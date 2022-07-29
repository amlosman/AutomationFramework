package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.helper.ElementActions;
import utilities.helper.VerifyActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerPage {
    WebDriver driver;
    By selectUser= By.id("userSelect");
    By loginBtn= By.xpath("//*[@type='submit']");
    By searchBox = By.xpath("//*[@type='text']");
    By accountRows = By.xpath("//tr[@ng-repeat=\"cust in Customers | orderBy:sortType:sortReverse | filter:searchCustomer\"]");
    By postCodeFilter= By.xpath("//*[contains(text(), 'Post Code')]");
    By postCodeValues= By.xpath("//tr/td[3]");
    By deleteBtn= By.xpath("//*[text()='Delete']");
    By welcomeMsg= By.xpath("//*[text()=' Welcome ']");
    By depositBtn= By.xpath("//*[contains(text(),'Deposit')]");
    By transactionsBtn= By.xpath("//*[contains(text(),'Transactions')]");
    By amountValue= By.xpath("//*[@type='number']");
    By submit= By.xpath("//*[@type='submit']");
    By successMsg=By.xpath("//*[@ng-show='message']");
    By transactionsTable = By.xpath("//tr");
    public CustomerPage(WebDriver driver){
        this.driver = driver;
    }
    public void selectUserByIndex(int userValue){
        ElementActions.clickOnElement(driver,selectUser);
        ElementActions.selectByIndex(driver,userValue,selectUser);
    }
    public void clickOnLogin(){
        ElementActions.clickOnElement(driver,loginBtn);
    }
    public void searchForText(String text) {
        ElementActions.sendKeys(driver, searchBox, text);
    }

    public int validateAccountRowIsDisplayed() {
        try {
            return driver.findElements(accountRows).size();
        }catch (Exception e){
            System.out.println("No Rows");
        }
        return 0;
    }
    public void sortByPostCode() {
        ElementActions.clickOnElement(driver, postCodeFilter);
    }
    public Boolean sortACS(){
        List<WebElement> postCode = driver.findElements(postCodeValues);
        List<String> value = new ArrayList<>();
        for(int i=1;i<postCode.size();i++) {
            value.add(postCode.get(i).getText());
        }
        List<String>sortValue=value;
        Collections.sort(value);
        for(int i=0;i<value.size();i++){
            if(!(value.get(i)).equals(sortValue.get(i)))
                return false;
        }
        return true;
    }
    public Boolean sortDES(){
        List<WebElement> postCode = driver.findElements(postCodeValues);
        List<String> value = new ArrayList<>();
        for(int i=1;i<postCode.size();i++) {
            value.add(postCode.get(i).getText());
        }
        List<String>sortValue=value;
        Collections.sort(value);
        for(int i=0,j=value.size()-1;i<value.size();i++,j--){
            if(!(value.get(i)).equals(sortValue.get(j)))
                return false;
        }
        return true;
    }
    public void clickOnDelete(){

        ElementActions.clickOnElement(driver,deleteBtn);
    }
    public Boolean validateWelcomeMsgIsDisplayed(){
        return VerifyActions.isDisplayed(driver,welcomeMsg);
    }
    public void clickOnDeposit(){
        ElementActions.clickOnElement(driver,depositBtn);
    }
    public void clickOnTransactionsBtn(){
        ElementActions.clickOnElement(driver,transactionsBtn);
    }
    public void setAmount(String keys){
        ElementActions.sendKeys(driver,amountValue,keys);
    }
    public void clickOnSubmit(){
        ElementActions.clickOnElement(driver,submit);
    }
    public String validateSuccessMsg(){
       return ElementActions.getElementText(driver, successMsg);
    }
    public boolean validateTransactionAdded(){
        VerifyActions.isDisplayed(driver,transactionsTable);
        return driver.findElements(transactionsTable).size()>=2;
    }
}
