package com.browserstack;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class AIAuthoringTest extends AppiumTest {

    @Test
    public void testLoginWithAIAgent() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Skip onboarding
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Tap the Skip button\"]}");

        // Search using AI Agent commands
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Tap on Search Wikipedia\"]}");
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Type BrowserStack in the search field\"]}");

        // Verify results
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Verify search results are displayed\"]}");
    }
}
