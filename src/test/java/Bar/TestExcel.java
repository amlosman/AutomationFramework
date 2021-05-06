package Bar;
import BasePackage.WebActionsForElement;
import FileWrappers.ExcelFileManager;
import Home.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import webBrowser.WebBrowsers;
import java.io.File;
public class TestExcel {

    WebDriver driver;
    String Actual;
    @BeforeClass
    public void beforeClass()throws Exception
    {
        driver = WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.valueOf("Chrome"),true);
        WebBrowsers.staticmaximizeWindow(driver);
    }

    @Test(dataProvider = "ElementLocator")
    public void CheckfreeText(String Elemet)
    {
        Actual= new HomePage(driver).navigateToHomePage().selectCreatAdecoment().clickOnButtoninDocumentItem(Elemet).
                getMessageIsFree(Elemet);
        Assert.assertEquals(Actual,"Free");
    }
    @DataProvider(name = "ElementLocator")
    public Object[][] ReadFromexcel()
    {
       ExcelFileManager excel = new ExcelFileManager(new File("src\\test\\resources\\WraperFiles\\TestData.xlsx"));
        excel.switchToSheet("DocmentPageLocators");

        /*Object[] [] Locator = {{excel.getCellData("Locators", 2)},
                                {excel.getCellData("Locators", 3)},
                                {excel.getCellData("Locators", 4)},
                                {excel.getCellData("Locators", 5)}};*/
        Object[][]Locator = new Object[4][1];
        for (int i=0;i<4;i++) {
            Locator[i][0] =excel.getCellData("Locators", i+2);
        }
           return Locator;
    }

    @AfterMethod
    public void takeScreenShoot(ITestResult result)
    {


            if (result.getStatus() == ITestResult.FAILURE)
            {
                System.out.println("Failed!");
                System.out.println("Taking Screenshot....");
                new WebActionsForElement(driver).takeScreenShoot(result.getName()+Actual);
            }

    }
    @AfterClass
    public void tearDown()
    {
        WebBrowsers.quitWindow(driver);
    }
}

