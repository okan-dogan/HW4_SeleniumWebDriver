package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class HW4_SelectDaysRandomly extends AbstractTestBase{

    @Test(description = "Select checkboxes randomly and print the name of the selected day.")
    public void selectCheckboxesRandomly(){

        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        BrowserUtilities.wait(5);
        List<WebElement> checkBoxesElements = driver.findElements(By.xpath("//*[@class='gwt-CheckBox']//input"));
        WebElement checkBoxFriday = driver.findElement(By.xpath("//*[@id='gwt-debug-cwCheckBox-Friday-input']"));
        WebElement clickedDay;

        int counterFriday = 3;
        for (int i = 0; i <counterFriday ; ) {

            clickedDay = checkBoxesElements.get(new Random().nextInt(checkBoxesElements.size()));
            clickedDay.click();
            BrowserUtilities.wait(1);
            String dayOfClicked = clickedDay.getAttribute("id").split("-")[3];
            System.out.println("You clicked = " + dayOfClicked);
            if(checkBoxFriday.isSelected()){
                counterFriday--;
            }
            clickedDay.click();
            BrowserUtilities.wait(1);

        }

    }


}