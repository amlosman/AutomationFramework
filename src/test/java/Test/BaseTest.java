package Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utilities.helper.ElementActions;
import utilities.helper.WebBrowsers;

public class BaseTest {
    WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser) {

        driver = WebBrowsers.chooseBrowserDriver((browser), true);
        WebBrowsers.staticmaximizeWindow(driver);
    }

    @AfterMethod
    public void takeScreenShoot() {
        ElementActions.takeScreenShoot(driver, "test.png");
    }

    @AfterClass
    public void tearDown() {
        WebBrowsers.quitWindow(driver);
    }
}
