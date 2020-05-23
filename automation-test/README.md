This is an automation test suite project that uses for PrimeFaces JSF components.

To execute the test battery:

- first run the primefaces-interface-test-webapp application 
- execute: `mvn test`

To generate surefire reports run the following commands:

```
$ mvn surefire-report:report-only
$ mvn site -DgenerateReports=false
```
