# testng-appium-app-browserstack

This repository demonstrates how to run Appium tests in [TestNG](http://testng.org) on BrowserStack App Automate.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Based on

These code samples are currently based on:

- **Appium-Java-Client:** `8.1.1`
- **Protocol:** `W3C`
## Setup

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

## Getting Started

Getting Started with Appium tests in TestNg on BrowserStack couldn't be easier!

### **Run first test :**

- Update `first.conf.json` file inside the `src/test/resources/com/browserstack/run_first_test` with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
- Run `mvn test -P first`

### **Speed up test execution with parallel testing :**

- Update `parallel.conf.json` file inside the `src/test/resources/com/browserstack/run_parallel_test` with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
- Run `mvn test -P parallel`

### **Use Local testing for apps that access resources hosted in development or testing environments :**

- Update `local.conf.json` file inside the `src/test/resources/com/browserstack/run_local_test` with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
- Run `mvn test -P local`

**Note**: If you are facing any issues, refer [Getting Help section](#Getting-Help)

## Integration with other Java frameworks

For other Java frameworks samples, refer to following repositories :

- [JUnit](https://github.com/browserstack/junit-appium-app-browserstack)
- [Java](https://github.com/browserstack/java-appium-app-browserstack)

Note: For other test frameworks supported by App-Automate refer our [Developer documentation](https://www.browserstack.com/docs/)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).