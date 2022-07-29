package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.helper.AlertHelper;
import utilities.helper.ElementActions;
import utilities.helper.VerifyActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerPage {
    WebDriver driver;
    By addCustomer = By.xpath("//*[@ng-class='btnClass1']");
    By openAccount = By.xpath("//*[@ng-class='btnClass2']");
    By customers = By.xpath("//*[@ng-class='btnClass3']");
    By firstName = By.xpath("//input[@placeholder='First Name']");
    By lastName = By.xpath("//input[@placeholder='Last Name']");
    By postCode = By.xpath("//input[@placeholder='Post Code']");
    By addCustomerSubmit = By.xpath("//*[@type='submit']");
    By selectUser = By.id("userSelect");
    By selectCurrency = By.id("currency");
    By process = By.xpath("//*[@type='submit']");
    ArrayList<Integer> customerIndex = new ArrayList<>();
    List<Integer> accountNumber = new ArrayList<>();

    public ManagerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCustomerDetails(String fName, String sName, String postC) {
        ElementActions.sendKeys(driver, firstName, fName);
        ElementActions.sendKeys(driver, lastName, sName);
        ElementActions.sendKeys(driver, postCode, postC);
        ElementActions.clickOnElement(driver, addCustomerSubmit);
    }

    public void clickOnAddCustomer() {
        ElementActions.clickOnElement(driver, addCustomer);
    }

    public boolean validateManagerActionsIsDisplayed() {
        return VerifyActions.isDisplayed(driver, addCustomer) && VerifyActions.isDisplayed(driver, openAccount) && VerifyActions.isDisplayed(driver, customers);
    }

    public boolean validateAddCustomerFormIsDisplayed() {
        return VerifyActions.isDisplayed(driver, addCustomerSubmit);
    }

    public String validatePopUpIsDisplayed() {
        String text = AlertHelper.getPopSuccessText(driver);
        String[] texts = text.split(":");
        customerIndex.add(Integer.parseInt(texts[1]));
        return text;
    }

    public int customerIndex() {
        return customerIndex.get(customerIndex.size()-1);
    }

    public void clickOnOkPopUp() {
        AlertHelper.acceptAlert(driver);
    }

    public void clickOnOpenAccount() {
        try {
            ElementActions.clickOnElement(driver, openAccount);
            ElementActions.clickOnElement(driver, openAccount);
            Thread.sleep(100);
        } catch  (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCustomerDetails(int customerValue, int currencyValue) {

        ElementActions.clickOnElement(driver, selectUser);
        ElementActions.selectByIndex(driver, customerValue, selectUser);
        ElementActions.clickOnElement(driver, selectCurrency);
        ElementActions.selectByIndex(driver, currencyValue, selectCurrency);
    }

    public void clickOnProcessToOpenAccount() {
        ElementActions.clickOnElement(driver, process);
    }

    public String getAddAccountMgs() {
        String text = AlertHelper.getPopSuccessText(driver);
        String[] texts = text.split(":");
        accountNumber.add( Integer.parseInt(texts[1]));
        return text;
    }

    public int getAccountNumber() {
        return accountNumber.get(accountNumber.size()-1);
    }

    public void clickOnCustomers() {
        try {
            ElementActions.clickOnElement(driver, customers);
            ElementActions.clickOnElement(driver, customers);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
