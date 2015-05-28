package net.billforward;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.gson.typeadapters.RuntimeTypeAdapterFactory;
import net.billforward.model.APIResponse;
import net.billforward.model.amendments.Amendment;
import net.billforward.model.gateways.APIConfiguration;
import net.billforward.model.gateways.GatewayTypeMapping;
import net.billforward.model.notifications.Notification;
import net.billforward.net.BillForwardResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BillForwardClient
{
	public String apiKey;
	public String apiUrl;
	
	public  final static String CHARSET = "UTF-8";
	private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";

	private boolean verifySSL = false;

	protected static BillForwardClient defaultClient;
	public static BillForwardClient getClient() throws APIException {
		if(defaultClient == null) {
			throw new APIException("Please set a valid BillForward client using BillForwardClient.makeDefaultClient(...) before calling methods", null);
		}
		return defaultClient;
	}
	
	public static void setDefaultClient(BillForwardClient client_) {
		defaultClient = client_;
	}	
	
	public static BillForwardClient makeDefaultClient(String apiKey_, String apiUrl_) {
		defaultClient = new BillForwardClient();
		defaultClient.setAPIKey(apiKey_);
		defaultClient.setAPIUrl(apiUrl_);
		
		return defaultClient;
	}
	
	/**
	 * (FOR TESTING ONLY)
	 * Only disable SSL verification if you're using your own (mocked) server.
	 * Disabling verification on billforward.net is not supported
	 */
	public void setVerifySSL(boolean verify) {
		verifySSL = verify;
	}

	public boolean getVerifySSL() {
		return verifySSL;
	}

	public String getApiUrl() {
		return apiUrl;
	}
	
	public static Gson GSON;
	
	static {
		/*
		 * This is to support polymorphism in the different type of API Configurations
		 */
		RuntimeTypeAdapterFactory<APIConfiguration> apiConfigAdapter = RuntimeTypeAdapterFactory.of(APIConfiguration.class, "@type");
		GatewayTypeMapping[] mappings = APIConfiguration.getTypeMappings();
		for(GatewayTypeMapping mapping : mappings) {
			apiConfigAdapter.registerSubtype((Class)mapping.getApiType(), mapping.getName());
		}

		/*
		 * This is to support polymorphism in the different type of Amendments
		 */
		RuntimeTypeAdapterFactory<Amendment> amendmentConfigAdapter = RuntimeTypeAdapterFactory.of(Amendment.class, "amendmentType");
		mappings = Amendment.getTypeMappings();
		for(GatewayTypeMapping mapping : mappings) {
			amendmentConfigAdapter.registerSubtype((Class)mapping.getApiType(), mapping.getName());
		}
		
		/*
		 * This is to support polymorphism in the different type of Notifications
		 */
		RuntimeTypeAdapterFactory<Notification> notificationConfigAdapter = RuntimeTypeAdapterFactory.of(Notification.class, "domain");
		mappings = Notification.getTypeMappings();
		for(GatewayTypeMapping mapping : mappings) {
			if(mapping.getName() == null) {
				notificationConfigAdapter.registerSubtype((Class)mapping.getApiType(), "default_value_mapping");				
			} else {
				notificationConfigAdapter.registerSubtype((Class)mapping.getApiType(), mapping.getName());				
			}
		}
		
		//2014-09-12T03:00:17Z
		//.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		
		GSON = new GsonBuilder()
		.registerTypeAdapter(Date.class, new DateTypeAdapter())
		.excludeFieldsWithoutExposeAnnotation()
		.registerTypeAdapterFactory(apiConfigAdapter)
		.registerTypeAdapterFactory(amendmentConfigAdapter)
		.registerTypeAdapterFactory(notificationConfigAdapter)
		.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		.create();
	}

	private static class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
		private final DateFormat dateFormat;
		
		private DateTypeAdapter() {
			dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		
		public Date deserialize(JsonElement jsonElement, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
			Date date = null;
			try {
				date = dateFormat.parse(jsonElement.getAsString());
			} catch (ParseException e) {
			}
			return date;
		}

		public JsonElement serialize(Date date, Type arg1, JsonSerializationContext arg2) {
			String dateFormatAsString = dateFormat.format(date);
			return new JsonPrimitive(dateFormatAsString);
		}	
	}
	
	/*
	 * Set this property to override your environment's default
	 * URLStreamHandler; Settings the property should not be needed in most
	 * environments.
	 */
	private static final String CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME = "net.billforward.net.customURLStreamHandler";

	public enum RequestMethod {
		GET, POST, DELETE, PUT
	}

	@SuppressWarnings("unused")
	private static String urlEncode(String str) throws UnsupportedEncodingException {
		// Preserve original behavior that passing null for an object id will lead
		// to us actually making a request to /v1/foo/null
		if (str == null) {
			return null;
		}
		else {
			return URLEncoder.encode(str, CHARSET);
		}
	}

	private Map<String, String> getHeaders(String apiKey) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-Charset", CHARSET);
		headers.put("User-Agent",
		String.format("BillForward/JavaBindings/%s", apiUrl));


		headers.put("Authorization", String.format("Bearer %s", apiKey));

		// debug headers
		String[] propertyNames = { "os.name", "os.version", "os.arch",
								   "java.version", "java.vendor", "java.vm.version",
								   "java.vm.vendor" };
		Map<String, String> propertyMap = new HashMap<String, String>();
		for (String propertyName : propertyNames) {
			propertyMap.put(propertyName, System.getProperty(propertyName));
		}
		propertyMap.put("lang", "Java");
		propertyMap.put("publisher", "BillForward");
		headers.put("X-BillForward-Client-User-Agent", GSON.toJson(propertyMap));
		return headers;
	}

	private java.net.HttpURLConnection createBillForwardConnection(String url, String apiKey) throws IOException {
		URL BillForwardURL = null;
		String customURLStreamHandlerClassName = System.getProperty(CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME, null);
		if (customURLStreamHandlerClassName != null) {
			// instantiate the custom handler provided
			try {
				Class<URLStreamHandler> clazz = (Class<URLStreamHandler>) Class.forName(customURLStreamHandlerClassName);
				Constructor<URLStreamHandler> constructor = clazz.getConstructor();
				URLStreamHandler customHandler = constructor.newInstance();
				BillForwardURL = new URL(null, url, customHandler);
			} catch (ClassNotFoundException e) {
				throw new IOException(e);
			} catch (SecurityException e) {
				throw new IOException(e);
			} catch (NoSuchMethodException e) {
				throw new IOException(e);
			} catch (IllegalArgumentException e) {
				throw new IOException(e);
			} catch (InstantiationException e) {
				throw new IOException(e);
			} catch (IllegalAccessException e) {
				throw new IOException(e);
			} catch (InvocationTargetException e) {
				throw new IOException(e);
			}
		} else {
			BillForwardURL = new URL(url);
		}
		java.net.HttpURLConnection conn = (java.net.HttpURLConnection) BillForwardURL.openConnection();
		conn.setConnectTimeout(30 * 1000);
		conn.setReadTimeout(80 * 1000);
		conn.setUseCaches(false);
		for (Map.Entry<String, String> header : getHeaders(apiKey).entrySet()) {
			conn.setRequestProperty(header.getKey(), header.getValue());
		}

		return conn;
	}


	private static String formatURL(String url, String query) {
		if (query == null || query.isEmpty()) {
			return url;
		} else {
			// In some cases, URL can already contain a question mark (eg, upcoming invoice lines)
			String separator = url.contains("?") ? "&" : "?";
			return String.format("%s%s%s", url, separator, query);
		}
	}

	private java.net.HttpURLConnection createGetConnection(String url, String query, String apiKey) throws IOException, APIConnectionException {
		String getURL = formatURL(url, query);
		java.net.HttpURLConnection conn = createBillForwardConnection(getURL, apiKey);
		conn.setRequestMethod("GET");

		return conn;
	}

	private java.net.HttpURLConnection createPostConnection(String url, String query, String apiKey) throws IOException, APIConnectionException {
		java.net.HttpURLConnection conn = createBillForwardConnection(url, apiKey);

		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", String.format("application/json;charset=%s", CHARSET));

		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;
	}

	private java.net.HttpURLConnection createPutConnection(String url, String query, String apiKey) throws IOException, APIConnectionException {
		java.net.HttpURLConnection conn = createBillForwardConnection(url, apiKey);

		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", String.format("application/json;charset=%s", CHARSET));

		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;
	}

	private java.net.HttpURLConnection createDeleteConnection(String url, String query, String apiKey) throws IOException, APIConnectionException {

	
		
		
		String deleteUrl = url; //formatURL(url, query);
		java.net.HttpURLConnection conn = createBillForwardConnection(deleteUrl, apiKey);

		//iansau: FYI: java < 1.8 does not support POST body on delete, sad times.
		conn.setRequestMethod("DELETE");		

		return conn;
	}


	// represents Errors returned as JSON
	private class Error {
		@Expose String errorType;
		@Expose String errorMessage;
		
		public String getErrorParameters() {
			return errorMessage;
		}
	}

	private static String getResponseBody(InputStream responseStream)
			throws IOException {
		//\A is the beginning of
		// the stream boundary
		@SuppressWarnings("resource")
		String rBody = new Scanner(responseStream, CHARSET).useDelimiter("\\A").next(); //

		responseStream.close();
		return rBody;
	}

	private BillForwardResponse makeURLConnectionRequest(RequestMethod method, String url, String query, String apiKey) throws APIConnectionException {
		java.net.HttpURLConnection conn = null;
		try {
			switch (method) {
			case GET:
				conn = createGetConnection(url, query, apiKey);
				break;
			case POST:
				conn = createPostConnection(url, query, apiKey);
				break;
			case PUT:
				conn = createPutConnection(url, query, apiKey);
				break;
			case DELETE:
				conn = createDeleteConnection(url, query, apiKey);
				break;
			default:
				throw new APIConnectionException(
						String.format(
								"Unrecognized HTTP method %s. "
										+ "This indicates a bug in the BillForward bindings. Please contact "
										+ "support@BillForward.net for assistance.",
								method));
			}
			// trigger the request
			int rCode = conn.getResponseCode();
			String rBody = null;
			Map<String, List<String>> headers;

			if (rCode >= 200 && rCode < 300) {
				rBody = getResponseBody(conn.getInputStream());
			} else {
				rBody = getResponseBody(conn.getErrorStream());
			}
			headers = conn.getHeaderFields();
			return new BillForwardResponse(rCode, rBody, headers);

		} catch (IOException e) {
			throw new APIConnectionException(
					String.format(
							"IOException during API request to BillForward (%s): %s "
									+ "Please check your internet connection and try again. If this problem persists,"
									+ "you should check BillForward's service status at https://twitter.com/BillForwardstatus,"
									+ " or let us know at support@BillForward.net.",
							getApiUrl(), e.getMessage()), e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}


	
	public <TRequest, TResponse> TResponse requestUntyped(RequestMethod method, String url, TRequest obj, Type responseType) throws AuthenticationException, 
																										 InvalidRequestException, 
																										 APIConnectionException, 
																										 CardException, 
																										 APIException {
		url = String.format("%s/%s", apiUrl, url);
		String originalDNSCacheTTL = null;
		Boolean allowedToSetTTL = true;
		try {
			originalDNSCacheTTL = java.security.Security.getProperty(DNS_CACHE_TTL_PROPERTY_NAME);
			// disable DNS cache
			java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "0");
		} catch (SecurityException se) {
			allowedToSetTTL = false;
		}

		try {
			return _requestUntyped(responseType, method, url, obj, apiKey);
		} finally {
			if (allowedToSetTTL) {
				if (originalDNSCacheTTL == null) {
					// value unspecified by implementation
					// DNS_CACHE_TTL_PROPERTY_NAME of -1 = cache forever
					java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "-1");
				} else {
					java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, originalDNSCacheTTL);
				}
			}
		}
	}
	
	public <T> APIResponse<T> request(RequestMethod method, String url, T obj, Type responseType) throws AuthenticationException, 
																										 InvalidRequestException, 
																										 APIConnectionException, 
																										 CardException, 
																										 APIException {
		url = String.format("%s/%s", apiUrl, url);
		String originalDNSCacheTTL = null;
		Boolean allowedToSetTTL = true;
		try {
			originalDNSCacheTTL = java.security.Security.getProperty(DNS_CACHE_TTL_PROPERTY_NAME);
			// disable DNS cache
			java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "0");
		} catch (SecurityException se) {
			allowedToSetTTL = false;
		}

		try {
			return _request(responseType, method, url, obj, apiKey);
		} finally {
			if (allowedToSetTTL) {
				if (originalDNSCacheTTL == null) {
					// value unspecified by implementation
					// DNS_CACHE_TTL_PROPERTY_NAME of -1 = cache forever
					java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "-1");
				} else {
					java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, originalDNSCacheTTL);
				}
			}
		}
	}

	
	protected <TRequest, TResponse> TResponse  _requestUntyped(Type responseType, RequestMethod method, String url, TRequest obj, String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		if ((apiKey == null || apiKey.length() == 0) && (apiKey == null || apiKey.length() == 0)) {
			throw new AuthenticationException(
					"No API key provided. (HINT: set your API key using 'BillForward.apiKey = <API-KEY>'. "
							+ "You can generate API keys from the BillForward web interface. "
							+ "See https://BillForward.com/api for details or email support@BillForward.net if you have questions.");
		}

		String query = "";
		if(obj != null) {
			query = GSON.toJson(obj);
		}

		BillForwardResponse response = makeURLConnectionRequest(method, url, query, apiKey);
		int rCode = response.getResponseCode();
		String rBody = response.getResponseBody();
		if (rCode < 200 || rCode >= 300) {
			handleAPIError(rBody, rCode);
		}
		
		
		TResponse acc = GSON.fromJson(rBody, responseType);
		return acc;
	}


	
	protected <T> APIResponse<T>  _request(Type responseType, RequestMethod method, String url, T obj, String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		if ((apiKey == null || apiKey.length() == 0) && (apiKey == null || apiKey.length() == 0)) {
			throw new AuthenticationException(
					"No API key provided. (HINT: set your API key using 'BillForward.apiKey = <API-KEY>'. "
							+ "You can generate API keys from the BillForward web interface. "
							+ "See https://BillForward.com/api for details or email support@BillForward.net if you have questions.");
		}

		String query = "";
		if(obj != null) {
			query = GSON.toJson(obj);
		}

		BillForwardResponse response = makeURLConnectionRequest(method, url, query, apiKey);
		int rCode = response.getResponseCode();
		String rBody = response.getResponseBody();
		if (rCode < 200 || rCode >= 300) {
			handleAPIError(rBody, rCode);
		}
		
		
		APIResponse<T> acc = GSON.fromJson(rBody, responseType);
		return (APIResponse<T>)acc;
	}

	private void handleAPIError(String rBody, int rCode)
			throws InvalidRequestException, AuthenticationException,
			CardException, APIException {
		Error error = GSON.fromJson(rBody, Error.class);
		switch (rCode) {
		case 400:
			throw new InvalidRequestException(error.errorMessage, error.getErrorParameters(), null);
		case 404:
			throw new InvalidRequestException(error.errorMessage, error.getErrorParameters(), null);
		case 401:
			throw new AuthenticationException(error.errorMessage);
		case 402:
			throw new CardException(error.errorMessage, error.getErrorParameters(), error.getErrorParameters(), null);
		default:
			throw new APIException(error.getErrorParameters(), null);
		}
	}

	public void setAPIKey(String apiKey_) {
		apiKey = apiKey_;		
	}

	public void setAPIUrl(String apiUrl_) {
		apiUrl = apiUrl_;		
	}
		
	public BillForwardClient() {
	}
}
