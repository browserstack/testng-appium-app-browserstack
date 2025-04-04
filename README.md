# testng-appium-app-browserstack

This repository demonstrates how to run Appium tests in [TestNG](http://testng.org) on BrowserStack App Automate using BrowserStack SDK.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Setup

### Requirements

1. Java 8+

    - If Java is not installed, follow these instructions:
        - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
        - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Maven (Only required if using Maven as the build tool)
   - If Maven is not downloaded, download it from [here](https://maven.apache.org/download.cgi)
   - For installation, follow the instructions [here](https://maven.apache.org/install.html)

3. Gradle (Only required if using Gradle as the build tool)
  - If Gradle is not downloaded, download it from [here](https://gradle.org/releases/)  
  - For installation, follow the instructions [here](https://gradle.org/install/) 

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

### **Run Sample test :**

- Switch to one of the following directories: [Android examples](android/testng-examples) or [iOS examples](ios/testng-examples)
- **For Maven:** Run the following command to execute tests in the Maven environment:  
    ```sh
    mvn test -P sample-test
    ```
- **For Gradle:** Run the following command to execute tests in the Gradle environment:  
    ```sh
    gradle clean sampleTest
    ```

### **Use Local testing for apps that access resources hosted in development or testing environments :**

- Simply configure the `browserstackLocal` parameter in the `browserstack.yml` file accordingly in [Android examples](android/testng-examples) or [iOS examples](ios/testng-examples).
  ```
  browserstackLocal: true
  ```
- You can use the `LocalSample` app provided in both folder [Android examples](android/testng-examples) or [iOS examples](ios/testng-examples) to run your test. Change the app parameter in the `browserstack.yml` file.

- **For Maven:** Run the following command to execute tests in the Maven environment:  
    ```sh
    mvn test -P sample-local-test
    ```
- **For Gradle:** Run the following command to execute tests in the Gradle environment:  
    ```sh
    gradle clean sampleLocalTest
    ```

-similarly for gradle you can do :- gradle clean sampleLocalTest


**Note**: If you are facing any issues, refer [Getting Help section](#Getting-Help)

## Integration with other Java frameworks

For other Java frameworks samples, refer to following repositories :

- [JUnit](https://github.com/browserstack/junit-appium-app-browserstack)
- [Java](https://github.com/browserstack/java-appium-app-browserstack)

Note: For other test frameworks supported by App-Automate refer our [Developer documentation](https://www.browserstack.com/docs/)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).