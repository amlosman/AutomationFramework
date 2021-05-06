package CreateDocument;

import FileWrappers.ReadFromPropertiesFile;
import BasePackage.WebActionsForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DocumentPage {
	
	WebDriver driver;
	String  ByElement;
	By Element;
	String  priceFreeSelector = ReadFromPropertiesFile.getValue("priceFreeSelector");

	 public  DocumentPage(WebDriver driver) {

	 	this.driver = driver;
	}
	public DocumentPage clickOnButtoninDocumentItem(String path) {

	 	driver =new WebActionsForElement(driver).clickonElement(path,WebActionsForElement.Locators.XPath,WebActionsForElement.ExpectedConditionsEnum.ElementToBeClickable);

		return this;
	} 
	 
	public String getMessageIsFree(String path) {

		return driver.findElement(new WebActionsForElement(driver).returnElementLocatorBy(path ,WebActionsForElement.Locators.XPath)).getText();

	}



}
