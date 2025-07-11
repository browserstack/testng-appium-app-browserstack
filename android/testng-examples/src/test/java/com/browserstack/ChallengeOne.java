package com.browserstack;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ChallengeOne extends AppiumTest {

    @Test
    public void testSamsungGalaxyS20UltraOrder() throws Exception {
        System.out.println("Starting Samsung Galaxy S20 Ultra order flow...");
        
        // Wait for app to load by waiting for the Filter & Sort button
        System.out.println("Waiting for app to load...");
        WebElement filterSortButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Filter & Sort']")));
        
        // Click Filter & Sort button in top right corner
        filterSortButton.click();
        
        // Select Samsung vendor
        WebElement samsungFilter = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Samsung']")));
        samsungFilter.click();
        
        // Select Price High to Low
        WebElement priceHighToLow = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='High to Low']")));
        priceHighToLow.click();
        
        // Close filter popup - try multiple approaches
        try {
            // Option 1: Look for a close button
            WebElement closeButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[contains(@text, 'Close')] | //android.widget.ImageView[@content-desc='Close']"));
            closeButton.click();
        } catch (Exception e1) {
            try {
                // Option 2: Press device back button
                driver.navigate().back();
            } catch (Exception e2) {
                // Option 3: The popup might close automatically after selections
                System.out.println("Filter popup may have closed automatically");
            }
        }
        
        Thread.sleep(3000); // Wait for filtered results to load
        
        // Add Galaxy S20 Ultra to cart
        
        WebElement addToCartButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(15)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Add to cart']")));
        addToCartButton.click();
        Thread.sleep(2000);

        // Go to cart and checkout
        WebElement cartIcon = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(15)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Cart'] | //android.widget.TextView[@text='🛒'] | //*[contains(@content-desc, 'cart')]")));
        cartIcon.click();
        Thread.sleep(2000);
        
        WebElement checkoutButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(15)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text=\"CHECKOUT\"]")));
        checkoutButton.click();

        // Click Username dropdown
        WebElement usernameDropdown = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Spinner[@content-desc='username-input']")));
        usernameDropdown.click();
        
        // Select "demouser" from dropdown
        WebElement demouserOption = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"demouser\"]")));
        demouserOption.click();
        
        Thread.sleep(1000);
        
        // Click Password dropdown  
        WebElement passwordDropdown = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Spinner[@content-desc='password-input']")));
        passwordDropdown.click();
        
        // Select "testingisfun99" from dropdown
        WebElement passwordOption = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"testingisfun99\"]")));
        passwordOption.click();
        
        Thread.sleep(1000);
        
        // Click Sign in button
        WebElement signInButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Sign in'] | //android.widget.Button[@text='Sign in']")));
        signInButton.click();
        

        // Fill checkout form
        WebElement firstNameInput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"firstNameInput\"]")));
        firstNameInput.sendKeys("John");
        
        WebElement lastNameInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"lastNameInput\"]"));
        lastNameInput.sendKeys("Doe");
        
        WebElement addressInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"addressInput\"]"));
        addressInput.sendKeys("123 Test Street");
        
        WebElement stateInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"stateInput\"]"));
        stateInput.sendKeys("Somewhere");

        WebElement postalInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"postalCodeInput\"]"));
        postalInput.sendKeys("12345");
        
        WebElement submitButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text=\"SUBMIT\"]")));
        submitButton.click();
        
        WebElement confirmationMessage = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(15)).until(
            ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Your Order has been successfully placed')]")));
        
        Assert.assertTrue(confirmationMessage.isDisplayed(), "Order confirmation should be displayed");
    }
}