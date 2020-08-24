package com.browserstack.run_local_test;
import com.browserstack.local.Local;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;


public class BrowserStackTestNGTest {
    public AndroidDriver<AndroidElement> driver;
    private Local local;

    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/com/browserstack/run_local_test/local.conf.json"));
        JSONArray envs = (JSONArray) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(0);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }
        
        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(capabilities.getCapability(pair.getKey().toString()) == null){
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if(username == null) {
            username = (String) config.get("username");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) config.get("access_key");
        }
        
        String app = System.getenv("BROWSERSTACK_APP_ID");
        if(app != null && !app.isEmpty()) {
          capabilities.setCapability("app", app);
        }

        if(capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true"){
            local = new Local();
            Map<String, String> options = new HashMap<String, String>();
            options.put("key", accessKey);
            local.start(options);
        }

        driver = new AndroidDriver(new URL("http://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities);
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed. 
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
        if(local != null) local.stop();
    }
}
