package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver("chrome");
        wait = new WebDriverWait(driver, 25);
        actions = new Actions(driver);
        BrowserUtilities.wait(2);

    }

    @AfterMethod
    public void teardown(){
        BrowserUtilities.wait(2);
        Driver.closeDriver();
    }

}
