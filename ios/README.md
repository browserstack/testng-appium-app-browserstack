## Setup

* Clone the repo
* Install dependencies `mvn compile`
* Update `*.conf.json` files inside the `src/test/resources/conf` directory with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)

## Running your tests
- To run a single test, run `mvn test -P single`
- To run parallel tests, run `mvn test -P parallel`
- To run local tests, run `mvn test -P local`

## Notes
* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
* Refer [Get Started](https://www.browserstack.com/app-automate/get-started#getting-started) document to configure the capabilities
* You can export the environment variables for the Username and Access Key of your BrowserStack account

  ```
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

## Additional Resources
* [Getting Started with App Automate](https://www.browserstack.com/app-automate/get-started)
