package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static WebDriver driver;

    private Driver(){

    }

    public static WebDriver getDriver(String driverName){

        driverName = driverName.toLowerCase();

        if(driver==null){
            if(driverName.equals("chrome")){
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
            }else if(driverName.equals("chromeheadless")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);
                driver= new ChromeDriver(chromeOptions);
            }else if(driverName.equals("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }else if(driverName.equals("edge")){
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }else{
                throw new RuntimeException ("Wrong browser name!");
            }
        }
        return driver;
    }


    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }


}
