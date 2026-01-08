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

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class AppiumTest {

    public AndroidDriver driver;

    @BeforeClass(alwaysRun=true)
    public void setUp() throws Exception {
        MutableCapabilities capabilities = new UiAutomator2Options();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        WebElement skipButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.presenceOfElementLocated(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")));
        skipButton.click();
    }

    @AfterClass(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
