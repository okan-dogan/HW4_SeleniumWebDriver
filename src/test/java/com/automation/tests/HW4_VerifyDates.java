package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class HW4_VerifyDates extends AbstractTestBase{

    @Test(description = "Test 1 out of 2")
    public void verifyTodaysDate(){

        driver.get("http://practice.cybertekschool.com/dropdown");

        WebElement year = driver.findElement(By.cssSelector("[id='year']"));
        WebElement month = driver.findElement(By.cssSelector("[id='month']"));
        WebElement day = driver.findElement(By.cssSelector("[id='day']"));

        Select selectY = new Select(year);
        Select selectM = new Select(month);
        Select selectD = new Select(day);

        String selectedYear = selectY.getFirstSelectedOption().getText();
        String selectedMonth = selectM.getFirstSelectedOption().getText();
        String selectedDay = selectD.getFirstSelectedOption().getText();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMMM/yyyy");
        Date date = new Date();
        String currentDate = formatter.format(date);
        String takenDate = String.format("%s/%s/%s", selectedDay, selectedMonth, selectedYear);
        assertEquals(takenDate, currentDate);

    }


    @Test(description = "Test 2 out of 2")
    public void verifyNumberOfDays(){

        driver.get("http://practice.cybertekschool.com/dropdown");

        WebElement year = driver.findElement(By.cssSelector("[id='year']"));
        WebElement month = driver.findElement(By.cssSelector("[id='month']"));

        Select selectY = new Select(year);
        Select selectM = new Select(month);

        List<WebElement> allYears = selectY.getOptions();

        WebElement randomYear = allYears.get(new Random().nextInt(allYears.size()));
        String takeAYear = randomYear.getText();
        int yearInt = Integer.parseInt(takeAYear);
        randomYear.click();
        System.out.println("takeAYear = " + takeAYear);

        for (int i = 0; i <12 ; i++) {
            selectM.selectByIndex(i);
            BrowserUtilities.wait(1);
            WebElement day = driver.findElement(By.cssSelector("[id='day']"));
            Select selectD = new Select(day);
            List<WebElement> allDays = selectD.getOptions();

            switch (i){
                case 0:
                    assertEquals(allDays.size(), 31);
                    System.out.println("Jan/31");
                    break;
                case 1:
                    if(yearInt%4==0){
                        assertEquals(allDays.size(), 29);
                        System.out.println("Feb/29");
                        break;
                    }
                    assertEquals(allDays.size(), 28);
                    System.out.println("Feb/28");
                    break;
                case 2:
                    assertEquals(allDays.size(), 31);
                    System.out.println("Mar/31");
                    break;
                case 3:
                    assertEquals(allDays.size(), 30);
                    System.out.println("Apr/30");
                    break;
                case 4:
                    assertEquals(allDays.size(), 31);
                    System.out.println("May/31");
                    break;
                case 5:
                    assertEquals(allDays.size(), 30);
                    System.out.println("Jun/30");
                    break;
                case 6:
                    assertEquals(allDays.size(), 31);
                    System.out.println("Jul/31");
                    break;
                case 7:
                    assertEquals(allDays.size(), 31);
                    System.out.println("Aug/31");
                    break;
                case 8:
                    assertEquals(allDays.size(), 30);
                    System.out.println("Sep/30");
                    break;
                case 9:
                    assertEquals(allDays.size(), 31);
                    System.out.println("Oct/31");
                    break;
                case 10:
                    assertEquals(allDays.size(), 30);
                    System.out.println("Nov/30");
                    break;
                case 11:
                    assertEquals(allDays.size(), 31);
                    System.out.println("Dec/31");
                    break;
            }

        }


    }


}
