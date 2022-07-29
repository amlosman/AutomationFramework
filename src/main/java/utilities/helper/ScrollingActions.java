package utilities.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.helper.ElementActions;

public class ScrollingActions {
    static JavascriptExecutor js;
    public static void scrollToTheEnd(WebDriver driver) {
        js= (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0,2500)");
    }
    public static void  scrollToFindElement(WebDriver driver, By locator) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", driver.findElement(locator));
        WaitHelper.waitElement(2000);
    }

    public void scrollUpToFindElement(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-350)", "");
    }

    public void scrollAndClickOnListItem(WebDriver driver,WebElement List, WebElement ListItem) {
        Actions action = new Actions(driver);
        //WebElement list = driver.findElement(By.id("sidebar"));
        action.moveToElement(List);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Now I need to scroll down till find my desire project in the list.
        js.executeScript("arguments[0].click();", ListItem);
    }
}
