# About

This is an automation test suite project that uses for PrimeFaces JSF components.

# Runnning

To execute the test battery:

- first run the primefaces-interface-test-webapp application 
- execute: `mvn test`

To generate surefire reports run the following commands:

```
$ mvn surefire-report:report-only
$ mvn site -DgenerateReports=false
```
The surefire report can be found in `target/site` folder. 