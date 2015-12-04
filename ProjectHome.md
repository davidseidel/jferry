Ferry is a java client lib to access restful webservices based on jax-rs (jsr-311).

Features:
  * easy to use
  * simple API
  * simple integration
  * support of:
    * JSON
    * XML

Example of usage:
```
// URL of RESTful WebService
String URL = "http://www.example.com:9998/person";

// create a service proxy
IService personService = JaxRsClientProxyFactory.createProxy(IService.class, URL);		
```

How to start:
See [QuickStartTutorial](QuickStartTutorial.md)