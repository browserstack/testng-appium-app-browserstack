package com.browserstack.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.browserstack.BrowserStackTestNGTest;

public class SuiteTest02 extends BrowserStackTestNGTest {

    @Test
    public void test_02() throws Exception {
        driver.get("https://www.google.com/ncr");
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 02");
        element.submit();
        Thread.sleep(5000);

        Assert.assertEquals("BrowserStack Test 02 - Google Search", driver.getTitle());
    }
}
