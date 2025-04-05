package utilities.helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.readers.ReadFromPropertiesFile;

import java.io.File;
import java.io.IOException;


public class ElementActions {

    public static void clickOnElement(WebDriver driver, By locator) {
        VerifyActions.isDisplayed(driver, locator);
        VerifyActions.isClickable(driver, locator);
        driver.findElement(locator).click();
    }

    public static void sendKeys(WebDriver driver, By locator, String keys) {

        VerifyActions.isClickable(driver, locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(keys);
    }

    public static void clickOnEnter(WebDriver driver) {
        WaitHelper.waitElement(500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();


    }

    public static void clickOnRight(WebDriver driver, By locator) {
        VerifyActions.isClickable(driver, locator);
        driver.findElement(locator).sendKeys(Keys.ARROW_RIGHT);
    }

    public static String getElementText(WebDriver driver, By locator) {
        VerifyActions.isDisplayed(driver, locator);
        return driver.findElement(locator).getText();
    }

    public static void clear(WebDriver driver, By locator) {
        driver.findElement(locator).clear();
    }

    public static WebElement getElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }


    public static void selectByIndex(WebDriver driver, int index, By locator) {
        WebElement dropList = driver.findElement(locator);
        Select dropDown = new Select(dropList);
        dropDown.selectByIndex(index);
    }

    public static void selectByValue(WebDriver driver, String value, By locator) {
        WebElement dropList = driver.findElement(locator);
        Select dropDown = new Select(dropList);
        dropDown.selectByValue(value);
    }



    public static void doubleClickOnButton(WebDriver driver, By element) {
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(element)).perform();
    }


    public static void DragDrop(WebDriver driver, WebElement source, WebElement Target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, Target).perform();
    }

    public static void takeScreenShoot(WebDriver driver, String error) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(error));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
