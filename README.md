authlete-java-common
====================

Overview
--------

Authlete Java library used commonly by service implementations and
the Authlete server.


License
-------

Apache License, Version 2.0


Maven
-----

```xml
<dependency>
    <groupId>com.authlete</groupId>
    <artifactId>authlete-java-common</artifactId>
    <version>1.0</version>
</dependency>
```


Source Download
---------------

    git clone https://github.com/authlete/authlete-java-common.git


JavaDoc
-------

[JavaDoc of authlete-java-common](http://authlete.github.com/auhlete-java-common/)


Example
-------

```java
// Create an instance of AuthleteConfiguration. This example uses
// AuthletePropertiesConfiguration which searches the file system
// or the class path for a properties file that stores the
// configuration parameters. See JavaDoc for other implementations
// of AuthleteConfiguration interface.
AuthleteConfiguration configuration
    = new AuthletePropertiesConfiguration(
        CONFIG_FILE, SECRET_KEY, INITIAL_VECTOR);

// Create an instance of AuthleteApi. This example uses a getInstance
// method variant which searches for known implementations of
// AuthleteApi interface. The current implementation only checks
// "com.authlete.client.jaxrs.api.AuthleteApiImpl" which is defined
// in com.authlete:authlete-java-client-jaxrs Maven package.
AuthleteApi api = AuthleteApiFactory.getInstance(configuration);

// Now Authlete APIs can be called. For example, the following code
// gets the services of the service owner. (getServiceList() does
// not all the services. See JavaDoc for details.)
ServiceListResponse response = api.getServiceList();
```


Links
-----

* [Authlete Home Page](https://www.authlete.com/)
* [Authlete Documents](https://www.authlete.com/documents.html)
* [Authlete Web APIs](https://www.authlete.com/authlete_web_apis.html)
