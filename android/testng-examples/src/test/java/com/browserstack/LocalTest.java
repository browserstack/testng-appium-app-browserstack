package com.browserstack;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
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
        try {
            WebElement searchElement = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.basicnetworking:id/test_action")));
            searchElement.click();

            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.className("android.widget.TextView")));

            WebElement testElement = findTextViewWithRetry("The active connection is", 3, 2000);

            if (testElement == null) {
                takeScreenshot("screenshot.png");
                throw new Error("Cannot find the needed TextView element from app after retries.");
            }

            String matchedString = testElement.getText();
            System.out.println(matchedString);

            Assert.assertTrue(matchedString.contains("The active connection is wifi"));
            Assert.assertTrue(matchedString.contains("Up and running"));
        } catch (Exception e) {
            takeScreenshot("error-screenshot.png");
            throw e;
        }
    }

    private WebElement findTextViewWithRetry(String textToMatch, int retries, int waitMillis) throws InterruptedException {
        WebElement matchedElement = null;

        for (int i = 0; i < retries; i++) {
            List<WebElement> allTextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));

            for (WebElement textElement : allTextViewElements) {
                if (textElement.getText().contains(textToMatch)) {
                    matchedElement = textElement;
                    break;
                }
            }
            if (matchedElement != null) {
                break;
            }
            Thread.sleep(waitMillis);
        }
        return matchedElement;
    }

    private void takeScreenshot(String fileName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = System.getProperty("user.dir") + File.separator + fileName;
            FileUtils.copyFile(scrFile, new File(filePath));
            System.out.println("Screenshot stored at " + filePath);
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}