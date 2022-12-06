package com.browserstack;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LocalTest extends AppiumTest {

    @Test
    public void test() throws Exception {
    WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.basicnetworking:id/test_action")));
    searchElement.click();
    WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.TextView")));

    WebElement testElement = null;
    List<WebElement> allTextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));
    Thread.sleep(10);
    for(WebElement textElement : allTextViewElements) {
      if(textElement.getText().contains("The active connection is")) {
        testElement = textElement;
      }
    }

    if(testElement == null) {
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "screenshot.png"));
      System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "screenshot.png");
      throw new Error("Cannot find the needed TextView element from app");
    }
    String matchedString = testElement.getText();
    System.out.println(matchedString);
    Assert.assertTrue(matchedString.contains("The active connection is wifi"));
    Assert.assertTrue(matchedString.contains("Up and running"));
    }
}
