package com.browserstack;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestNGSample {
  private String platform;
  private String browserName;
  private String browserVersion;

  @Factory(dataProvider = "getBrowsers")
  public TestNGSample(String platform,String browserName,String browserVersion) {
    this.platform = platform;
    this.browserName = browserName;
    this.browserVersion = browserVersion;
  }

  private WebDriver driver;

  @BeforeMethod(alwaysRun=true)
  public void setUp() throws Exception {
    DesiredCapabilities capability = new DesiredCapabilities();
    capability.setCapability("platform",platform);
    capability.setCapability("browser", browserName);
    capability.setCapability("browserVersion", browserVersion);
    capability.setCapability("build", "Sample TestNG Tests");
    capability.setCapability("project", "Series Tests");
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    driver = new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@hub.browserstack.com/wd/hub"), capability);
  }

  @Test(groups = { "series_test" })
  public void testSimple() throws Exception {
    this.driver.get("http://www.google.com");
    System.out.println("Page title is: " + driver.getTitle());
    Assert.assertEquals("Google", driver.getTitle());
    WebElement element = driver.findElement(By.name("q"));
    element.sendKeys("BrowserStack");
    element.submit();
    driver = new Augmenter().augment(driver);
    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(srcFile, new File("Screenshot.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @AfterMethod(alwaysRun=true)
  public void tearDown() throws Exception {
    if(driver != null) {
      driver.quit();
    }
  }

  @DataProvider(name = "getBrowsers")
  public static Object[][] createData1() {
    return new Object[][] {
      { Platform.WINDOWS.toString(), "chrome", "48" },
      { Platform.XP.toString(), "firefox", "44"},
    };
  }
}
