# About

This is an automation test suite project that uses for PrimeFaces JSF components.

# Runnning

To execute the test battery:

- first run the sibling project primefaces-interface-test-webapp application 
- then, execute: 

```
mvn clean test -Dselenium.base.url=http://localhost:8080/primefaces-test-webapp/ -Dwebdriver.chrome.driver=drivers\chromedriver.exe -Dwebdriver.gecko.driver=D:\drivers\geckodriver.exe -Dselenium.run.headless=true -Dselenium.quit.browser.after.run=true

```
This command will run the full battery of test cases. You can limit the test cases by using `-Dtest` parameter. For example the command:

```
mvn clean test -Dselenium.base.url=http://localhost:8080/primefaces-test-webapp/ -Dwebdriver.chrome.driver=D:\drivers\chromedriver.exe -Dwebdriver.gecko.driver=D:\drivers\geckodriver.exe -Dselenium.run.headless=true -Dselenium.quit.browser.after.run=true -Dtest=org.primefaces.component.fileupload.cases.messagetemplate.advanced.auto.*Test
```
limits the execution for tests in package `org.primefaces.component.fileupload.cases.messagetemplate.advanced.auto.*` only.

Please check the Configuration section below for more details.

## Configuration

- Make sure you have the web drivers
 - [chromedriver](https://chromedriver.chromium.org/) 
 - 
 
 executables in somewhere of your computer and set the parameters `-Dwebdriver.chrome.driver` and  `webdriver.gecko.driver` properly.

- `-Dselenium.run.headless` can be set to `false` to show the browser screen during each test run.
- If `selenium.quit.browser.after.run` is `false` the browser screen will keep visible after the test finishes. 

## Generating reports

To generate surefire reports run the following commands:

```
$ mvn surefire-report:report-only
$ mvn site -DgenerateReports=false
```
The surefire report can be found in `target/site` folder. 


 