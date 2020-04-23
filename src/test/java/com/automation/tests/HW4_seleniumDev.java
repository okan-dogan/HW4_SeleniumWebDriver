package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class HW4_seleniumDev extends AbstractTestBase{

    @Test(description = "Verify all a tags valid")
    public void verifyValidLinks(){

        driver.get("https://www.selenium.dev/documentation/en/");
        BrowserUtilities.wait(2);

        List<WebElement> tagAElements = driver.findElements(By.xpath("//a"));
        HttpURLConnection huc = null;
        int brokenLinksCounter = 0;
        int validLinksCounter = 0;
        for (WebElement eachTagAElement : tagAElements) {
            String eachLink = eachTagAElement.getAttribute("href");
            try {
                huc = (HttpURLConnection) new URL(eachLink).openConnection();
                huc.setRequestMethod("HEAD");
                huc.connect();
                int responseCode = huc.getResponseCode();
                if(responseCode>=400){
                    System.out.println(eachLink+" is a broken link");
                    brokenLinksCounter++;
                }else{
                    System.out.println(eachLink+" is a working link");
                    validLinksCounter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Broken link count = "+brokenLinksCounter);
        System.out.println("Valid link count = "+brokenLinksCounter);

        assertEquals(0, brokenLinksCounter);

    }


}
