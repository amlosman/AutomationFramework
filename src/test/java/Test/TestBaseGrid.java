package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBaseGrid {

	public static String BaseUrl = "https://www.levelset.com/";
	protected ThreadLocal<RemoteWebDriver> driver=null;

	@BeforeClass
	@Parameters(value = {"browser"})
	public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
		driver = new ThreadLocal<>();
		DesiredCapabilities caps = new DesiredCapabilities();
		//caps.setCapability("browserName" , browser);
		caps.setBrowserName(browser);
		driver.set(new RemoteWebDriver (new URL("http://localhost:4444/wd/hub"),caps));
		getDriver().navigate().to(BaseUrl);
	}

	public WebDriver getDriver()
	{
		return driver.get();
	}
	@AfterClass
	public void stopDriver()
	{
		getDriver().quit();
		driver.remove();
	}

}
