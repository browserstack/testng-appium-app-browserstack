package com.browserstack;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.time.Duration;

public class ParallelTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {
      WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));
      searchElement.click();
      WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
      insertTextElement.sendKeys("BrowserStack");
      Thread.sleep(5000);

      List<WebElement> allProductsName = driver.findElements(AppiumBy.className("android.widget.TextView"));
      Assert.assertTrue(allProductsName.size() > 0);
    }
}
