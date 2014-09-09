package net.billforward.model;

import java.lang.reflect.Type;

public class ResourcePath {
	protected String m_path;
	protected String m_entityName;
	protected Type m_responseType;
	
	public ResourcePath(String path_, String entityName_, Type responseType_) {
		m_path = path_;
		m_entityName = entityName_;
		m_responseType = responseType_;
	}

	/***
	 * Gets base API route for this entity.
	 * @return Path
	 */
	public String getPath() {
		return m_path;
	}

	/***
	 * Gets the XmlRootElement of the Entity. We see this in @type field of a response.
	 * @return string
	 */
	public String getEntityName() {
		return m_entityName;
	}

	/**
	 * The expected return type.
	 * @return Type
	 */
	public Type getResponseType() {
		return m_responseType;
	}
}
