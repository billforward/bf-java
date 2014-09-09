# BillForward Java Bindings

Sign up for a BillForawrd account at https://www.billforward.net.

Requirements
============

Java 1.5 and later.

Installation
============

### Maven users

Add this dependency to your project's POM:

    <dependency>
      <groupId>net.billforward</groupId>
      <artifactId>billforward-java</artifactId>
      <version>1.0.0</version>
    </dependency>
    
### Gradle users

Add this dependency to your project's build file:

    compile "net.billforward:billforward-java:1.0.0"

### Others

You'll need to manually install the following JARs:

* The BillForward JAR from https://code.billforward.net/billforward-java-latest.jar
* [Google Gson](http://code.google.com/p/google-gson/) from <http://google-gson.googlecode.com/files/google-gson-2.2.4-release.zip>.

Usage
=====

BillForwardExample.java



See [BillForward.java](https://github.com/...) for examples.

Testing
=======

You must have Maven installed. To run the tests, simply run `mvn test`. You can run particular tests by passing `-D test=Class#method` -- for example, `-D test=BillForward#testAcountCreate`.