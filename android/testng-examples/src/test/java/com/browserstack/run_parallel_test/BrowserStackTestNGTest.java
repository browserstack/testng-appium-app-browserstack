package com.browserstack.run_parallel_test;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileReader;
import java.time.Duration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;


public class BrowserStackTestNGTest {
    public AndroidDriver driver;

    @BeforeMethod(alwaysRun=true)
    @org.testng.annotations.Parameters(value={"deviceIndex"})
    public void setUp(String deviceIndex) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/com/browserstack/run_parallel_test/parallel.conf.json"));
        JSONArray envs = (JSONArray) config.get("environments");

        UiAutomator2Options options = new UiAutomator2Options();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(Integer.parseInt(deviceIndex));
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

        driver = new AndroidDriver(new URL("http://"+config.get("server")+"/wd/hub"), options);
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed. 
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }
}
