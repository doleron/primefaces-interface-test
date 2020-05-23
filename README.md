# primefaces-interface-test

An automation test suit for PrimeFaces JSF components.

In the current stage, there are tests for [FileUpload](https://www.primefaces.org/showcase/ui/file/upload/multiple.xhtml) component only.

The tests running using the following drivers:

- Chromium ChromeDriver: https://chromedriver.chromium.org/
- Mozilla GeckoDriver: https://github.com/mozilla/geckodriver

This project consists of two sub projects:

- primefaces-interface-test-webapp is a webapp to be used for test PrimeFaces JSF components
- primefaces-automation-suite a battery of junit/selenium tests.

Check each project to know how to build/run them.