# testng-appium-app-browserstack

[TestNG](http://testng.org) Integration with BrowserStack.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

### Requirements

1. Java 8+

    - If Java is not installed, follow these instructions:
        - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
        - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Maven
   - If Maven is not downloaded, download it from [here](https://maven.apache.org/download.cgi)
   - For installation, follow the instructions [here](https://maven.apache.org/install.html)

### Install the dependencies

To install the dependencies for Android tests, run :
```sh
cd android/testng-examples
mvn clean
```

Or,

To install the dependencies for iOS tests, run :

```sh
cd ios/testng-examples
mvn clean
```

### Update `username` and `access_key` keys in *.conf.json

- Update *.conf.json files for `username`, `access_key` keys inside the [android/testng-examples/src/test/resources/](android/testng-examples/src/test/resources) for Android examples and inside [ios/testng-examples/src/test/resources](ios/testng-examples/src/test/resources) for iOS examples with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings). 

- Note: You can also export the environment variables for the Username and Access Key of your BrowserStack account. 

     ```
     export BROWSERSTACK_USERNAME=<browserstack-username> &&
     export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
     ```      

### Upload app and update `app` key in *.conf.json

- Android
   - Upload your Native App (.apk or .aab file) to BrowserStack servers using upload API:

      ```
      curl -u "username:access_key" -X POST "https://api-cloud.browserstack.com/app-automate/upload" -F "file=@/path/to/app/file/Application-debug.apk"
      ```
   -  If you do not have an .apk file and looking to simply try App Automate, [you can download our sample app and upload](https://www.browserstack.com/app-automate/sample-apps/android/WikipediaSample.apk)
     to the BrowserStack servers using the above API.

   - You can use public url as well to upload your app

     ```
     curl -u "username:access_key" \
     -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
     -F "url=https://www.browserstack.com/app-automate/sample-apps/android/WikipediaSample.apk"
     ```   
  - Please note the value of app_url in the API response (bs://f7c874f21852.... in the above examples). 

  - Update *.conf.json with value of `app_url`(got from response) for `app` key

    - Files inside the [android/testng-examples/src/test/resources/](android/testng-examples/src/test/resources) for Android examples and inside [ios/testng-examples/src/test/resources](ios/testng-examples/src/test/resources) for iOS examples.


## Getting Started

Getting Started with Appium tests in TestNg on BrowserStack couldn't be easier!

### **Run first test :**

- Switch to `run_first_test` directory under [Android examples](android/testng-examples) or [iOS examples](ios/testng-examples)
- Then run
    ```sh
    mvn test -P first
    ```
- Alternatively, you can follow the steps outlined in the documentation - [Get Started with your first test on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/testng)

### **Speed up test execution with parallel testing :**

- Switch to `run_parallel_test` directory under [Android examples](android/testng-examples/) or [iOS examples](ios/testng-examples/)
- Then run
    ```sh
    mvn test -P parallel
    ```

- Alternatively, you can follow the steps outlined in the documentation - [Get Started with your parallel test on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/testng/parallelize-tests)

### **Use Local testing for apps that access resources hosted in development or testing environments :**

- If you do not have an local .apk file and looking to simply try local for  App Automate, [you can download our sample local app and upload](https://www.browserstack.com/app-automate/sample-apps/android/LocalSample.apk)
     to the BrowserStack servers using the same app upload API.

- Switch to `run-local-test` directory under [Android examples](android/testng-examples/) or [iOS examples](ios/testng-examples/)
- Then run
    ```sh
    mvn test -P local
    ```
- Alternatively, you can follow the steps outlined in the documentation - [Get Started with Local testing on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/testng/local-testing)

**Note**: If you are facing any issues, refer [Getting Help section](#Getting-Help)

## Integration with other Java frameworks

For other Java frameworks samples, refer to following repositories :

- [JUnit](https://github.com/browserstack/junit-appium-app-browserstack)
- [Java](https://github.com/browserstack/java-appium-app-browserstack)

Note: For other test frameworks supported by App-Automate refer our [Developer documentation](https://www.browserstack.com/docs/)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).