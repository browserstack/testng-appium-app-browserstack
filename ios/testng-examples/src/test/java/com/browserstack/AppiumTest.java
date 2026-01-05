package com.browserstack;

import java.net.URL;
import java.time.Duration;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;


public class AppiumTest {

    public IOSDriver driver;

    @BeforeClass(alwaysRun=true)
    public void setUp() throws Exception {
        MutableCapabilities options = new XCUITestOptions();
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),options);
        WebElement textButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Button")));
        textButton.click();
    }

    @AfterClass(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
