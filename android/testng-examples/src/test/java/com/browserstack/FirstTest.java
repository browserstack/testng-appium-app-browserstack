package com.browserstack;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class FirstTest extends AppiumTest {

    @Test
    public void test() throws Exception {
      WebElement skipButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.presenceOfElementLocated(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")));
      skipButton.click();

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
