# About

This is an automation test suite project that uses for PrimeFaces JSF components.

# Runnning

To execute the test battery:

- first run the sibling project primefaces-interface-test-webapp application 
- then, execute: 

```
mvn test -Dselenium.base.url=http://localhost:8080/primefaces-test-webapp/ -Dwebdriver.chrome.driver=drivers\chromedriver.exe -Dselenium.run.headless=true -Dselenium.quit.browser.after.run=true

```
This command will run all the test cases. You can limit the test cases using `-Dtest` parameter
For example:

```
mvn test -Dselenium.base.url=http://localhost:8080/primefaces-test-webapp/ -Dwebdriver.chrome.driver=drivers\chromedriver.exe -Dselenium.run.headless=true -Dselenium.quit.browser.after.run=true -Dtest=org.primefaces.component.fileupload.cases.messagetemplate.advanced.auto.*Test
```
limits the execution for tests in package `org.primefaces.component.fileupload.cases.messagetemplate.advanced.auto.*` only.

Please check the Configuration section below for more details.

## Configuration

- Make sure you haev the [chromedriver](https://chromedriver.chromium.org/) executable in somewhere of your computer and set `-Dwebdriver.chrome.driver` according.

- `-Dselenium.run.headless` can be set to `false` to show the browser screen during each test run.
- If `selenium.quit.browser.after.run` is `false` the browser screen will keep visible after the test finishes. 

## Generating reports

To generate surefire reports run the following commands:

```
$ mvn surefire-report:report-only
$ mvn site -DgenerateReports=false
```
The surefire report can be found in `target/site` folder. 


 