package utilities.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitHelper {
    public void ImplicitWait(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    public void ExplicitWait(WebDriver driver ,By element)
    {
        WebDriverWait wait = new WebDriverWait(driver,200);
        VerifyActions.isClickable(driver,element);
    }
    public void fluentWait(WebDriver driver, By  element)
    {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(20,TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        WebElement element1 = wait.until(new Function<WebDriver,WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(element);
            }
        });
    }
    public static void waitElement(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
