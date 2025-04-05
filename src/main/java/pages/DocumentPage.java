package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.helper.ElementActions;
import utilities.readers.ReadFromPropertiesFile;

public class DocumentPage {
	
	WebDriver driver;
	By DocumentFreeItem= By.xpath("//div[text()='Lien Waiver']/..//span[text()='Free']");
	By Element;
	String  priceFreeSelector = ReadFromPropertiesFile.getValue("priceFreeSelector");

	 public  DocumentPage(WebDriver driver) {

	 	this.driver = driver;
	}
	public DocumentPage clickOnButtonInDocumentItem() {
	 	ElementActions.clickOnElement(driver,DocumentFreeItem);
		return this;
	} 
	 
	public String getMessageIsFree(String path) {

return null;
	}



}
