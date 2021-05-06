package Home;
import FileWrappers.ReadFromPropertiesFile;
import BasePackage.WebActionsForElement;
import CreateDocument.DocumentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;
	String createElement = ReadFromPropertiesFile.getValue("creatDocument");

	String url = ReadFromPropertiesFile.getValue("URLHomePage");
	public  HomePage(WebDriver driver) {
		this.driver=driver;

	}

	public HomePage navigateToHomePage() {
		
		WebElement element;
		int i=0;
		do {
		     driver.get(url);
		      return this;

		}
		while(i<3);
	}
	public DocumentPage selectCreatAdecoment() {
		return new DocumentPage(new WebActionsForElement(driver).clickonElement(createElement,WebActionsForElement.Locators.linkText,WebActionsForElement.ExpectedConditionsEnum.ElementToBeClickable));
	}
}
