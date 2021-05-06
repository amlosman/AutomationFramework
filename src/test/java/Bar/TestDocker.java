package Bar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class TestDocker {
    public ThreadLocal<RemoteWebDriver> driver=null;

    @BeforeClass
    @Parameters(value = {"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        driver = new ThreadLocal<>();
         if(browser.equals("chrome")) {
             DesiredCapabilities caps = new DesiredCapabilities();
             System.setProperty("webdriver.chrome.driver", "/var/lib/jenkins/.m2/repository/webdriver/chromedriver/linux64/83.0.4103.39/chromedriver");
             caps.setCapability("applicationCacheEnabled", true);
             ChromeOptions options = new ChromeOptions();
             options.setBinary("/usr/bin/google-chrome-stable");
             options.addArguments("--headless");
             options.addArguments("--no-sandbox");
             options.addArguments("--remote-debugging-port=9222");
             options.addArguments("--disable-infobars");
             options.addArguments("--disable-dev-shm-usage"); //Linux
             options.addArguments("--disable-browser-side-navigation");
             options.addArguments("--disable-gpu"); //Windows
             options.addArguments("--disable-web-security");
             caps.setCapability(ChromeOptions.CAPABILITY, options);
             caps.setBrowserName("chrome");
             driver.set(new RemoteWebDriver (new URL("http://localhost:4444/wd/hub"),caps));

         }
         else
         {
             DesiredCapabilities caps = new DesiredCapabilities();
             caps.setBrowserName("firefox");
             driver.set(new RemoteWebDriver (new URL("http://localhost:4444/wd/hub"),caps));

         }
    }

    @Test
    public void test()
    {
        driver.get().navigate().to("https://www.levelset.com/");
    }
    @AfterClass
    public void stopDriver()
    {
        driver.get().quit();
    }
}
