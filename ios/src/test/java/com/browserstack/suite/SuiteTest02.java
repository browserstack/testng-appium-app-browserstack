package com.browserstack.suite;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;

import com.browserstack.BrowserStackTestNGTest;

public class SuiteTest02 extends BrowserStackTestNGTest {

  @Test
  public void test_02() throws Exception {
    IOSElement loginButton = (IOSElement) new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Log In")));
    loginButton.click();
    IOSElement emailTextField = (IOSElement) new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Email address")));
    emailTextField.sendKeys("hello02@browserstack.com");

    driver.findElementByAccessibilityId("Next").click();
    Thread.sleep(5000);

    List<IOSElement> textElements = driver.findElementsByXPath("//XCUIElementTypeStaticText");
    Assert.assertTrue(textElements.size() > 0);
    String matchedString = "";
    for(IOSElement textElement : textElements) {
      String textContent = textElement.getText();
      if(textContent != null && textContent.contains("not registered")) {
        matchedString = textContent;
      }
    }

    System.out.println(matchedString);
    Assert.assertTrue(matchedString.contains("not registered on WordPress.com"));
  }
}
