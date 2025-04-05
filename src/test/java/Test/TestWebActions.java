package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.helper.*;


import java.util.List;

public class TestWebActions {
    WebDriver driver;
    WebElement element;
    @BeforeClass
    public void beforeClass()
    {
        driver = WebBrowsers.chooseBrowserDriver("Chrome",false);
        WebBrowsers.staticmaximizeWindow(driver);
    }
    //@Test
    public void testDoubleClik()
    {
        driver.get("http://cookbook.seleniumacademy.com/DoubleClickDemo.html");
         ElementActions.doubleClickOnButton(driver,By.id("message"));
        Assert.assertEquals("rgba(255, 255, 0, 1)" ,element.getCssValue("background-color"));

    }
    @Test
    public void testDragDrop()
    {
        driver.get("http://cookbook.seleniumacademy.com/DragDropDemo.html");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

         ElementActions.DragDrop(driver,source,target);
        Assert.assertEquals("Dropped!",target.getText());

    }
    //@Test
    public void TestAlertSimple()
    {
        driver.get("http://cookbook.seleniumacademy.com/Alerts.html");
         element = driver.findElement(By.id("simple"));
         element.click();
        AlertHelper.sendKeysToAlert(driver,"Hello! I am an alert box!");
    }
    @Test
    public void TestAlertPrompetAcepted()
    {
        driver.get("http://cookbook.seleniumacademy.com/Alerts.html");
        element = driver.findElement(By.id("prompt"));
        WebElement AssertE = driver.findElement(By.id("prompt_demo"));
        element.click();
        AlertHelper.acceptAlert(driver);

    }
    //@Test
    public void TestAlertPrompetcanceled() {
        driver.get("http://cookbook.seleniumacademy.com/Alerts.html");
        element = driver.findElement(By.id("prompt"));
        WebElement AssertE = driver.findElement(By.id("prompt_demo"));
        element.click();
        AlertHelper.cancelAlert(driver);


    }

    //@Test
    public void TestScrollByJS() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");

        ScrollingActions.scrollToTheEnd(driver);
        Thread.sleep(2000);


    }
    //@Test
    public void testBrokenImages()
    {
        driver.navigate().to("https://the-internet.herokuapp.com/broken_images");
        driver.manage().window().maximize();

        int invalidImageCount = 0 ;
        List<WebElement> imageList = driver.findElements(By.tagName("img"));
        for(WebElement imgElement : imageList)
        {
            if (imgElement != null)
            {
                invalidImageCount+= VerifyActions.verifyImageActive(imgElement);
            }
        }
        System.out.println("Total no. of invalid Images are : " + invalidImageCount);
    }
    //@Test
    public void testBrokenLinks()
    {
        driver.navigate().to("http://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total Links are: "+ links.size());
        for (int i = 0 ; i < links.size() ; i++ )
        {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");
              VerifyActions.VerifyLink(url);
        }
    }

    @Test
    public void testDownloadFile() throws InterruptedException
    {
        driver.navigate().to("http://the-internet.herokuapp.com/download");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[contains(text(), 'floodlight_01.PNG')]")).click();
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown()
    {
        WebBrowsers.quitWindow(driver);
    }
}
