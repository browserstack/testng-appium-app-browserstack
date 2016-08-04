package com.browserstack.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.browserstack.BrowserStackTestNGTest;

public class SuiteTest09 extends BrowserStackTestNGTest {

    @Test
    public void test_09() throws Exception {
        driver.get("https://www.google.com/ncr");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 09");
        Thread.sleep(3000);
        element.submit();
        Thread.sleep(3000);

        Assert.assertEquals("BrowserStack Test 09 - Google Search", driver.getTitle());
    }
}
