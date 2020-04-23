package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class HW4_AmazonRelated extends AbstractTestBase{

    @Test(description = "Test 1 out of 7")
    public void verifyDepartmentAll(){

        driver.get("http://www.amazon.com");

        WebElement departmentAll = driver.findElement(By.xpath("//*[text()='All']"));
        String actualDepartmentName = departmentAll.getText();
        String expectedDepartmentName = "All";
        assertEquals(actualDepartmentName, expectedDepartmentName);
    }

    @Test(description = "Test 2 out of 7")
    public void verifyDepartmentsAlphabeticalOrder(){

        driver.get("http://www.amazon.com");

        WebElement departmentsSelect = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
        Select select = new Select(departmentsSelect);
        List<WebElement> allOptionElements = select.getOptions();
        List<String> allOptionStringsOriginal = new ArrayList<>();
        for (int i = 0; i <allOptionElements.size() ; i++) {
            allOptionStringsOriginal.add(allOptionElements.get(i).getText());
        }

        List<String> allOptionStringsSorted = new ArrayList<>(allOptionStringsOriginal);
        Collections.sort(allOptionStringsSorted);

        assertNotEquals(allOptionStringsSorted, allOptionStringsOriginal);

        System.out.println("allOptionStringsOriginal = " + allOptionStringsOriginal);
        System.out.println("allOptionStringsSorted = " + allOptionStringsSorted);

    }


    @Test(description = "Test 3 out of 7")
    public void verifyMainDepartments(){

        driver.get("https://www.amazon.com/gp/site-directory");
        BrowserUtilities.wait(3);

        WebElement departmentsSelect = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
        Select select = new Select(departmentsSelect);
        List<WebElement> allOptionElements = select.getOptions();
        List<String> allOptionStringsOriginal = new ArrayList<>();
        for (int i = 0; i <allOptionElements.size() ; i++) {
            allOptionStringsOriginal.add(allOptionElements.get(i).getText());
        }

        List<WebElement> mainDepartmentsWebElements = driver.findElements(By.xpath("//h2"));
        List<String> mainDepartmentsList = new ArrayList<>();
        for (WebElement eachWebElement : mainDepartmentsWebElements) {
            mainDepartmentsList.add(eachWebElement.getText());
        }


        int countSame = 0;
        for (int i = 0; i <mainDepartmentsList.size() ; i++) {
            for (int j = 0; j < allOptionStringsOriginal.size(); j++) {
                if (mainDepartmentsList.get(i).equals(allOptionStringsOriginal.get(j))) {
                    countSame++;
                }
            }
        }

        System.out.println("All options in the 'All' tab has = "+allOptionStringsOriginal.size()+" items = " + allOptionStringsOriginal);
        System.out.println("All main departments name list on the page has = "+ mainDepartmentsList.size()+" items = "+ mainDepartmentsList);
        System.out.println("Matched items count = " + countSame);


        assertEquals(mainDepartmentsList.size(), countSame);

// elements for the subtitle under departments
//        List<WebElement> mainDepartmentsWebElements = driver.findElements(By.xpath("//*[@class='a-column a-span3 fsdColumn fsdColumn_3']//*[@href]"));
//        List<String> mainDepartmentsList = new ArrayList<>();
//        for (WebElement eachWebElement : mainDepartmentsWebElements) {
//            mainDepartmentsList.add(eachWebElement.getText());
//        }

    }

    @Test(description = "Test 4 out of 7")
    public void verifyCart(){

        driver.get("http://www.amazon.com");
        BrowserUtilities.wait(1);

        WebElement amazonSearchBox = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
        amazonSearchBox.sendKeys("wooden spoon", Keys.ENTER);
        BrowserUtilities.wait(5);


        WebElement amazonFirstItem = driver.findElement(By.xpath("(//h2)[20]"));
        String firstItemName = amazonFirstItem.getText();


        WebElement amazonFirstItemPriceElement = driver.findElement(By.xpath("(//h2)[20]//..//..//*[@class='a-offscreen']"));
        String firstItemPrice = amazonFirstItemPriceElement.getAttribute("textContent");

        WebElement amazonFirstItemImg = driver.findElement(By.xpath("(//h2)[20]//..//..//img"));

        amazonFirstItemImg.click();
        BrowserUtilities.wait(5);


        WebElement amazonFirstItemQuantityElement = driver.findElement(By.xpath("//*[@id='a-autoid-0-announce']//*[@class='a-dropdown-prompt']"));
        assertEquals("1", amazonFirstItemQuantityElement.getText());
        System.out.println("amazonFirstItemQuantityElement.getText() = " + amazonFirstItemQuantityElement.getText());

        WebElement afterClickFirstItemPriceElement = driver.findElement(By.xpath("//*[@id='priceblock_ourprice']"));
        String afterClickFirstItemPrice = afterClickFirstItemPriceElement.getText();
        assertEquals(firstItemPrice, afterClickFirstItemPrice);
        System.out.println("firstItemPrice = " + firstItemPrice);
        System.out.println("afterClickFirstItemPrice = " + afterClickFirstItemPrice);

        WebElement afterClickAmazonFirstItem = driver.findElement(By.xpath("//*[@id='productTitle']"));
        String afterClickFirstItemName = afterClickAmazonFirstItem.getText();
        assertEquals(firstItemName, afterClickFirstItemName);
        System.out.println("firstItemName = " + firstItemName);
        System.out.println("afterClickFirstItemName = " + afterClickFirstItemName);

        WebElement addCartButton = driver.findElement(By.xpath("//*[@id='add-to-cart-button']"));
        assertTrue(addCartButton.isDisplayed());
        System.out.println("addCartButton.isDisplayed() = " + addCartButton.isDisplayed());

    }


    @Test(description = "Test 5 out of 7")
    public void verifyPrimeLabel(){

        driver.get("http://www.amazon.com");
        BrowserUtilities.wait(1);

        WebElement amazonSearchBox = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
        amazonSearchBox.sendKeys("wooden spoon", Keys.ENTER);
        BrowserUtilities.wait(3);

        WebElement amazonFirstPrimeElement = driver.findElement(By.xpath("(//*[@alt='Prime'])[1]//..//*[@data-click-el='title']"));
        String amazonFirstPrimeName = amazonFirstPrimeElement.getAttribute("textContent");

        WebElement primeCheckbox = driver.findElement(By.xpath("//*[@aria-label='Prime Eligible']//*[@class='a-icon a-icon-checkbox']"));
        primeCheckbox.click();
        BrowserUtilities.wait(1);

        WebElement amazonFirstPrimeElementAfterClickingPrime = driver.findElement(By.xpath("(//*[@alt='Prime'])[1]//..//*[@data-click-el='title']"));
        String amazonFirstPrimeNameAfterClickingPrime = amazonFirstPrimeElementAfterClickingPrime.getAttribute("textContent");
        System.out.println("amazonFirstPrimeName = " + amazonFirstPrimeName);
        System.out.println("amazonFirstPrimeNameAfterClickingPrime = " + amazonFirstPrimeNameAfterClickingPrime);
        assertEquals(amazonFirstPrimeName, amazonFirstPrimeNameAfterClickingPrime);

        List<WebElement> amazonBrandsElements = driver.findElements(By.xpath("//*[@aria-labelledby='p_89-title']//*[@class='a-icon a-icon-checkbox']"));
        amazonBrandsElements.get(amazonBrandsElements.size()-1).click();
        BrowserUtilities.wait(1);

        WebElement amazonFirstPrimeElementAfterClickingLastBrand = driver.findElement(By.xpath("(//*[@aria-label='Amazon Prime'])[1]/../../../../../..//h2/a/span"));
        String amazonFirstPrimeNameAfterClickingLastBrand = amazonFirstPrimeElementAfterClickingLastBrand.getAttribute("textContent");
        System.out.println("amazonFirstPrimeNameAfterClickingLastBrand = " + amazonFirstPrimeNameAfterClickingLastBrand);
        assertNotEquals(amazonFirstPrimeName, amazonFirstPrimeNameAfterClickingLastBrand);


    }


    @Test(description = "Test 6 out of 7")
    public void verifyBrandNamesWithPrime(){

        driver.get("http://www.amazon.com");
        BrowserUtilities.wait(1);

        WebElement amazonSearchBox = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
        amazonSearchBox.sendKeys("wooden spoon", Keys.ENTER);
        BrowserUtilities.wait(3);

        List<WebElement> amazonBrandsElements = driver.findElements(By.xpath("//*[@aria-labelledby='p_89-title']//*[@class='a-size-base a-color-base']"));
        List<String> amazonBrandsName = new ArrayList<>();
        for (WebElement eachElement : amazonBrandsElements) {
            amazonBrandsName.add(eachElement.getAttribute("textContent"));
        }

        WebElement primeCheckbox = driver.findElement(By.xpath("//*[@aria-label='Prime Eligible']//*[@class='a-icon a-icon-checkbox']"));
        primeCheckbox.click();
        BrowserUtilities.wait(1);

        List<WebElement> amazonPrimeBrandsElements = driver.findElements(By.xpath("//*[@aria-labelledby='p_89-title']//*[@class='a-size-base a-color-base']"));
        List<String> amazonPrimeBrandsName = new ArrayList<>();
        for (WebElement eachElement : amazonPrimeBrandsElements) {
            amazonPrimeBrandsName.add(eachElement.getAttribute("textContent"));
        }

        assertEquals(amazonPrimeBrandsName, amazonBrandsName);
        System.out.println("amazonBrandsName.toString() = " + amazonBrandsName.toString());
        System.out.println("amazonPrimeBrandsName.toString() = " + amazonPrimeBrandsName.toString());


    }







}
