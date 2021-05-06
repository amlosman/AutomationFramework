package Bar;

import BasePackage.WebActionsForElement;
import FileWrappers.ExcelFileManager;
import FileWrappers.ReadDataFromJSonFile;
import FileWrappers.ReadFromPropertiesFile;
import FileWrappers.ReadOrWriteData;
import Home.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import webBrowser.WebBrowsers;

import java.io.File;

public class BaseTest {


   WebDriver driver;
    String Elemet  ="//div[text()='Notice of Intent to Lien']/..//span[text()='Free']";
    String Actual;
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser)throws Exception
    {

        driver =WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.valueOf(browser ),true);
        WebBrowsers.staticmaximizeWindow(driver);
    }

@Test
    public void CheckfreeText()
    {
      Actual= new HomePage(driver).navigateToHomePage().selectCreatAdecoment().clickOnButtoninDocumentItem(Elemet).
             getMessageIsFree(Elemet);
     Assert.assertEquals(Actual,"Free");

    }
    @AfterMethod
    public void takeScreenShoot()
    {
            new WebActionsForElement(driver).takeScreenShoot(Actual);

    }

    @AfterClass
    public void tearDown()
    {
        WebBrowsers.quitWindow(driver);
    }
}
