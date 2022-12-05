package com.browserstack;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class FirstTest extends AppiumTest {

  @Test
  public void test() throws Exception {
    WebElement textButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Button")));
    textButton.click();
    WebElement textInput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Input")));
    textInput.sendKeys("hello@browserstack.com"+"\n");

    Thread.sleep(5000);

    WebElement textOutput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Output")));

    Assert.assertEquals(textOutput.getText(),"hello@browserstack.com");
  }
}
