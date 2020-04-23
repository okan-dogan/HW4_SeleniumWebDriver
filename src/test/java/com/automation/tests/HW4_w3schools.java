package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;


public class HW4_w3schools extends AbstractTestBase {

    @Test(description = "Print displayed tag a")
    public void printTagA(){

        driver.get("https://www.w3schools.com/");
        BrowserUtilities.wait(2);

        List<WebElement> tagAElements = driver.findElements(By.xpath("//a"));
        int counterOfDisplayed = 0;
        for (WebElement eachTagAElement : tagAElements) {
            if(eachTagAElement.isDisplayed()){
                System.out.println("The text of the element = "+eachTagAElement.getText());
                System.out.println("the link of the element = "+eachTagAElement.getAttribute("href"));
                System.out.println();
                counterOfDisplayed++;
            }
        }
        System.out.println("Total number of displayed element = "+counterOfDisplayed);

    }


}
