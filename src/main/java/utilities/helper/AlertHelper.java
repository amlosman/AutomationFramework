package utilities.helper;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertHelper {
    public static void acceptAlert(WebDriver driver ) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public static void cancelAlert(WebDriver driver ) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

    }
    public static String getPopSuccessText(WebDriver driver) {

        try {
            Thread.sleep(100);
            return driver.switchTo().alert().getText();

        } catch (Exception e) {
            System.out.println("No Alert displayed");
        }
        return null;

    }
    public static void sendKeysToAlert(WebDriver driver,String keys)
    {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(keys);
        alert.accept();
    }
}
