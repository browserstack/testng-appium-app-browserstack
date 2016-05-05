package com.browserstack;

import com.browserstack.local.Local;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

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

public class TestNGLocal {
  private String platform;
  private String browserName;
  private String browserVersion;
  private Local bsLocal;

  @Factory(dataProvider = "getBrowsers")
  public TestNGLocal(String platform,String browserName,String browserVersion) {
    this.bsLocal = new Local();
    this.platform = platform;
    this.browserName = browserName;
    this.browserVersion = browserVersion;
  }

  private WebDriver driver;

  @BeforeSuite(alwaysRun=true)
  public void suiteSetup() throws Exception {
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

    HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
    bsLocalArgs.put("key", accessKey);
    bsLocalArgs.put("forcelocal", "");
    bsLocal.start(bsLocalArgs);
  }

  @BeforeMethod(alwaysRun=true)
  public void setUp() throws Exception {
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

    DesiredCapabilities capability = new DesiredCapabilities();
    capability.setCapability("platform",platform);
    capability.setCapability("browser", browserName);
    capability.setCapability("browserVersion", browserVersion);
    capability.setCapability("name", "Sample TestNG Local  Test");
    capability.setCapability("build", "Sample TestNG Tests");
    capability.setCapability("browserstack.local", "true");

    driver = new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@hub.browserstack.com/wd/hub"), capability);
  }

  @Test(groups = { "local_test" })
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

  @AfterSuite(alwaysRun=true)
  public void afterSuite() throws Exception {
    if(bsLocal != null) {
      bsLocal.stop();
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
