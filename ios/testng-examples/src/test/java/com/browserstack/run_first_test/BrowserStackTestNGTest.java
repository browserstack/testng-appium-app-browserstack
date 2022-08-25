package com.browserstack.run_first_test;
import com.browserstack.local.Local;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import io.appium.java_client.ios.options.XCUITestOptions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;


public class BrowserStackTestNGTest {
  public IOSDriver driver;

  @BeforeMethod(alwaysRun=true)
  public void setUp() throws Exception {
    JSONParser parser = new JSONParser();
    JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/com/browserstack/run_first_test/first.conf.json"));
    JSONArray envs = (JSONArray) config.get("environments");

    XCUITestOptions options = new XCUITestOptions();

    Map<String, String> envCapabilities = (Map<String, String>) envs.get(0);
    Iterator it = envCapabilities.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      options.setCapability(pair.getKey().toString(), pair.getValue().toString());
    }
    
    Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
    it = commonCapabilities.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      if(options.getCapability(pair.getKey().toString()) == null){
          options.setCapability(pair.getKey().toString(), pair.getValue());
      }
    }

    HashMap<String, Object> browserstackOptions = (HashMap<String, Object>) config.get("browserstackOptions");
    options.setCapability("bstack:options", browserstackOptions);

    String username = System.getenv("BROWSERSTACK_USERNAME");
    if(username == null) {
      username = (String) browserstackOptions.get("userName");
    }

    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    if(accessKey == null) {
      accessKey = (String) browserstackOptions.get("accessKey");
    }
    
    String app = System.getenv("BROWSERSTACK_APP_ID");
    if(app != null && !app.isEmpty()) {
      options.setCapability("app", app);
    }

    driver = new IOSDriver(new URL("http://"+config.get("server")+"/wd/hub"), options);
  }

  @AfterMethod(alwaysRun=true)
  public void tearDown() throws Exception {
    // Invoke driver.quit() to indicate that the test is completed. 
    // Otherwise, it will appear as timed out on BrowserStack.
    driver.quit();
  }
}
