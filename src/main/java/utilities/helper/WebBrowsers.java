package utilities.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebBrowsers {

    public static WebDriver chooseBrowserDriver(String browserName, boolean headless) {
        //using chromeoption
        switch (browserName) {
            case "Chrome": {
                WebDriverManager.chromedriver().clearDriverCache().setup();
                return new ChromeDriver((ChromeOptions) getOptions(browserName,headless));
            }
            case "Firefox": {
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                    return new FirefoxDriver();
            }
            case "Edge": {
                WebDriverManager.edgedriver().setup();
                EdgeDriver edgeDriver = new EdgeDriver();
                return edgeDriver;
            }
            default:
//                Assert.fail();
                return null;
        }

    }


    private static Object getOptions(String option,boolean headless) {

        switch (option) {
            case "Chrome": {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--lang=en");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-popup-blocking");
                return chromeOptions;
            }
            case "Firefox": {
                FirefoxOptions options1 = new FirefoxOptions();
                options1.setHeadless(headless);
                return options1;
            }

            default:
                return null;
        }
    }

    public static void staticmaximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void quitWindow(WebDriver driver) {
        driver.quit();
    }

}
