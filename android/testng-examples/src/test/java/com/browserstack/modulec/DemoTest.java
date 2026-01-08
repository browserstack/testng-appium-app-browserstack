package com.browserstack.modulec;

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

    @Test(groups = {"regression"})
    public void flakyTest_intermittentlyPassesAndFails() throws Exception {
        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));

        searchElement.click();
        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
        String searchText = Math.random() > 0.7 ? "BrowserStack" : "sldkjlksljlksjlfs";
        insertTextElement.sendKeys(searchText);
        Thread.sleep(5000);

        List<WebElement> allProductsName = driver.findElements(AppiumBy.className("android.widget.TextView"));
        Assert.assertTrue(allProductsName.size() > 0);
    }

    @Test
    public void alwaysFailingTest_missingElement1() throws Exception {
        WebElement missingElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/missing_element")));
        missingElement.click();
    }

    @Test(groups = {"p1"})
    public void alwaysFailingTest_sameStacktrace1() throws Exception {
        WebElement missingElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/common_incorrect_element")));
        missingElement.click();
    }

    @Test(groups = {"regression", "p1"})
    public void alwaysFailingTest_sameStacktrace2() throws Exception {
        WebElement missingElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/common_incorrect_element")));
        missingElement.click();
    }

    @Test(retryAnalyzer = Retry.class)
    public void testWithFrameworkLevelRetry_2RetriesConfigured() {
        Integer result = Math.random() > 0.7 ? 6 : 9;
        Assert.assertEquals((int) result, 6 + 3);
    }

    @Test(retryAnalyzer = Retry.class)
    public void anotherTestWithFrameworkLevelRetry_2RetriesConfigured() {
        Integer result = Math.random() > 0.7 ? 6 : 9;
        Assert.assertEquals((int) result, 3 + 3);
    }

    @Test
    public void alwaysPassingTest_exampleA() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"regression", "must_pass"})
    public void alwaysPassingTest_exampleB() {
        Assert.assertTrue(true);
    }

    @Test
    public void alwaysPassingTest_exampleC() {
        Assert.assertTrue(true);
    }

    @Test
    public void alwaysPassingTest_exampleD() {
        Assert.assertTrue(true);
    }
}
