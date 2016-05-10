TestNG-BrowserStack
=========

Sample for running [TestNG] tests with BrowserStack Automate.

### Configuring the json
- Open `src/com/browserstack/TestNGSample.java` or `src/com/browserstack/TestNGParallel.java`.
- Add `username` and `accessKey` with your BrowserStack credentials. Don't have one? Get one on BrowserStack [dashboard].
- Add / customise more [capabilities].
- Optionally, you can add your BrowserStack credentials to the environment variables `BROWSERSTACK_USERNAME` and `BROWSERSTACK_ACCESS_KEY`.

### Running the tests
- To start local tests run: `ant test-local`
- To start tests in series, run: `ant test-series`
- To start parallel tests run: `ant test-parallel`

[TestNG]:http://testng.org
[capabilities]:http://www.browserstack.com/automate/capabilities
[dashboard]:https://www.browserstack.com/automate
