package com.browserstack.suite;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;

import com.browserstack.BrowserStackTestNGTest;

public class SuiteTest04 extends BrowserStackTestNGTest {

  @Test
  public void test_04() throws Exception {
    IOSElement loginButton = (IOSElement) new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Log In")));
    loginButton.click();
    IOSElement emailTextField = (IOSElement) new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Email address")));

    // element.sendKeys() method is not supported in Appium 1.6.3
    // Workaround for sendKeys() method:
    emailTextField.click();
    String email = "hello.d@browserstack.com";
    for (int i = 0; i < email.length(); i++) {
      driver.findElementByXPath("//XCUIElementTypeKey[@name='" + email.charAt(i) + "']").click();
    }

    driver.findElementByAccessibilityId("Next").click();
    Thread.sleep(5000);

    List<IOSElement> textElements = driver.findElementsByXPath("//XCUIElementTypeStaticText");
    Assert.assertTrue(textElements.size() > 0);
    String matchedString = "";
    for(IOSElement textElement : textElements) {
      String textContent = textElement.getText();
      if(textContent.contains("not registered")) {
        matchedString = textContent;
      }
    }

    System.out.println(matchedString);
    Assert.assertTrue(matchedString.contains("not registered on WordPress.com"));
  }
}
