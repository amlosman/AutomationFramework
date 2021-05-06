package Bar;
import BasePackage.WebActionsForElement;
import Home.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import webBrowser.WebBrowsers;

public class TestBar {

	 WebDriver driver;

	String dayprelNote = "20-Day Preliminary Notice";
	 String  noticeOfInternt = "Notice of Intent to Lien";
	 String lienWaiver ="Lien Waiver";
	 String lienBondlaim = "'Lien / Bond Claim'";
	String Actaul;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser)throws Exception
	{

		driver =WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.valueOf(browser),false);
		WebBrowsers.staticmaximizeWindow(driver);
	}

	
	@Test
	public void noticeOfInterntTestCaseFree()
	{
		 Actaul = new HomePage(driver).navigateToHomePage().selectCreatAdecoment().clickOnButtoninDocumentItem(lienBondlaim).
				getMessageIsFree(lienBondlaim);
		Assert.assertEquals("Free",Actaul);
	}


	/*@AfterMethod
	public void takeScreenShoot()
	{
		//if(Actaul !="Free")
		new WebActionsForElement(driver).takeScreenShoot();

	}*/
	@AfterClass
	public void tearDown()
	{
		new WebActionsForElement(driver).takeScreenShoot(Actaul);
		WebBrowsers.quitWindow(driver);
	}
	
	
}
