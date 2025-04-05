package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.helper.ElementActions;
import utilities.helper.ScrollingActions;
import utilities.helper.VerifyActions;

public class HomePage {
    By mainHeader = By.className("mainHeading");
    By customerLoginBtn = By.xpath("//*[text()='Customer Login']");
    By bankManagerLoginBtn = By.xpath("//*[text()='Bank Manager Login']");
    By homePage= By.xpath("//*[contains(text(),'Home')]");
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnBankManagerLogin() {
        ElementActions.clickOnElement(driver, bankManagerLoginBtn);
    }

    public void clickOnCustomerLogin() {
        ElementActions.clickOnElement(driver, customerLoginBtn);
    }
    public void clickOnHomeBtn() {
        ElementActions.clickOnElement(driver, homePage);
    }
}