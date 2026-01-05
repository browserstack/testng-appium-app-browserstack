package com.browserstack.modulea;

import com.browserstack.AppiumTest;
import com.browserstack.Retry;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DemoTest extends AppiumTest {

    @Test(groups = {"regression", "p1"})
    public void flakyTest_intermittentlyPassesAndFails() throws Exception {
        WebElement textInput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Input")));
        String inputText = Math.random() > 0.7 ? "hello@browserstack.com" : "dlsjfkjlskjd";
        textInput.sendKeys(inputText+"\n");

        Thread.sleep(5000);

        WebElement textOutput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Output")));

        Assert.assertEquals(textOutput.getText(),"hello@browserstack.com");
    }

    @Test
    public void alwaysFailingTest_missingElement1() throws Exception {
        throw new Exception("CustomException: Unable to locate element id xyz in app");
    }

    @Test
    public void alwaysFailingTest_sameStacktrace1() throws Exception {
        throw new Exception("InvalidParamsException: Received method signature does not match expected signature");
    }

    @Test
    public void alwaysFailingTest_sameStacktrace2() throws Exception {
        throw new Exception("InvalidParamsException: Received method signature does not match expected signature");
    }

    @Test(retryAnalyzer = Retry.class)
    public void testWithFrameworkLevelRetry_2RetriesConfigured() {
        Integer result = Math.random() > 0.7 ? 6 : 9;
        try {
            Assert.assertEquals((int) result, 6 + 3);
        } catch (Exception e) {
            throw new RuntimeException("Test failed. Will retry if more retries are configured, else will mark test as failed.");
        }
    }

    @Test(retryAnalyzer = Retry.class)
    public void anotherTestWithFrameworkLevelRetry_2RetriesConfigured() {
        Integer result = Math.random() > 0.7 ? 6 : 9;
        try {
            Assert.assertEquals((int) result, 3 + 3);
        } catch (Exception e) {
            throw new RuntimeException("Test failed. Will retry if more retries are configured, else will mark test as failed.");
        }
    }

    @Test
    public void alwaysPassingTest_exampleA() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"p1"})
    public void alwaysPassingTest_exampleB() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"regression", "must_pass"})
    public void alwaysPassingTest_exampleC() {
        Assert.assertTrue(true);
    }

    @Test
    public void alwaysPassingTest_exampleD() {
        Assert.assertTrue(true);
    }
}
