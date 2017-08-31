package com.browserstack;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LocalTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {
    WebElement searchElement = new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(By.id("com.example.android.basicnetworking:id/test_action")));
    searchElement.click();
    WebElement insertTextElement = new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(By.className("android.widget.TextView")));

    WebElement testElement = null;
    List<WebElement> allTextViewElements = driver.findElements(By.className("android.widget.TextView"));
    Thread.sleep(10);
    for(WebElement textElement : allTextViewElements) {
      System.out.println(textElement.getText());
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
