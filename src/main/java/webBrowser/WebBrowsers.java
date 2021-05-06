package webBrowser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class WebBrowsers {

    public static WebDriver chooseBrowserDriver(BrowserEnum browswerName ,boolean hedless) {
        //using chromeoption
      switch(browswerName) {
          case Chrome: {
                WebDriverManager.chromedriver().setup();
                if(hedless)
                    return new  ChromeDriver((ChromeOptions) getChromeOptions(browswerName));
                return new ChromeDriver();
            }
            case Firefox: {
                WebDriverManager.firefoxdriver().setup();
                if(hedless)
                    return new FirefoxDriver((FirefoxOptions) getChromeOptions(browswerName));
                return new FirefoxDriver();

            }
            case Edge: {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
           default :

                Assert.fail();
                return  null;
        }


      /*using PhantomJSDriver
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "E:\\testing\\TA Ahmed reda\\TestAutomation\\Levelset\\Drivers\\phantomjs.exe");
        caps.setCapability("takesScreenshot", true);
        String[] phantomArgs = new String[] { "--webdriver-loglevel=NONE" };
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
        return new PhantomJSDriver(caps);*/
    }


    private static Object getChromeOptions(BrowserEnum option)
    {

        switch (option) {
            case Chrome:{
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            return options;
            }
            case Firefox: {
                FirefoxOptions options1 = new FirefoxOptions();
                options1.setHeadless(true);
                return options1;
            }

            default:
                return null;
        }
    }

    public static void staticmaximizeWindow(WebDriver driver)
    {
        driver.manage().window().maximize();
    }
    public static void quitWindow(WebDriver driver)
    {
        driver.quit();
    }

    public enum BrowserEnum
    {
        Firefox,
        Edge,
        Chrome


    }
}
