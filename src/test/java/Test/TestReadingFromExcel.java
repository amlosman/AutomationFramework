package Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CustomerPage;
import pages.HomePage;
import pages.ManagerPage;
import utilities.readers.ExcelFileManager;

import java.io.File;


public class TestReadingFromExcel extends BaseTest {
    HomePage homePage ;
    ManagerPage managerPage ;
    CustomerPage customerPage ;
    Object[][] Locator;

    @Test(description = "Validate user can click on login manager button")
    public void validateUserClickOnManager() {
        homePage = new HomePage(driver);
        managerPage = new ManagerPage(driver);
        customerPage = new CustomerPage(driver);
        homePage.clickOnBankManagerLogin();
        managerPage.validateManagerActionsIsDisplayed();
        managerPage.clickOnAddCustomer();
    }

    @Test(description = "Validate user can add new customer", dataProvider = "CustomersData", dependsOnMethods = "validateUserClickOnManager")
    public void validateUsersSendRequestWithValidData(String fName, String lName, String postCode) {
        managerPage.validateAddCustomerFormIsDisplayed();
        managerPage.fillCustomerDetails(fName, lName, postCode);
        Assert.assertEquals(managerPage.validatePopUpIsDisplayed(), "Customer added successfully with customer id :" + managerPage.customerIndex());
        managerPage.clickOnOkPopUp();
    }

    @Test(description = "Validate user can open new account", dependsOnMethods = "validateUsersSendRequestWithValidData")
    public void openNewAccount() {
        managerPage.clickOnOpenAccount();
        managerPage.selectCustomerDetails(managerPage.customerIndex(), 1);
        managerPage.clickOnProcessToOpenAccount();
        Assert.assertEquals(managerPage.getAddAccountMgs(), "Account created successfully with account Number :" + managerPage.getAccountNumber());
        managerPage.clickOnOkPopUp();
    }

    @Test(description = "Validate user can sort by post code", dependsOnMethods = "openNewAccount")
    public void validateUserCanSortByPostcode() {
        homePage.clickOnHomeBtn();
        homePage.clickOnBankManagerLogin();
        managerPage.clickOnCustomers();
        customerPage.sortByPostCode();
        Assert.assertTrue(customerPage.sortACS());
        customerPage.sortByPostCode();
    }

    @Test(description = "Validate user can search by firstName", dependsOnMethods = "validateUserCanSortByPostcode")
    public void validateUserCanSearchByFName() {
        homePage.clickOnHomeBtn();
        homePage.clickOnBankManagerLogin();
        managerPage.clickOnCustomers();
        customerPage.searchForText(Locator[0][0].toString());
        Assert.assertEquals(customerPage.validateAccountRowIsDisplayed(), 1);
    }

    @Test(description = "Validate User can delete customer", dependsOnMethods = "validateUserCanSearchByFName")
    public void validateUserCanDeleteCustomer() {
        customerPage.clickOnDelete();
        Assert.assertEquals(customerPage.validateAccountRowIsDisplayed(), 0);
    }

    @Test(description = "Validate user can login by new customer", dependsOnMethods = "validateUserCanDeleteCustomer")
    public void validateCustomerAdded() {
        homePage.clickOnHomeBtn();
        homePage.clickOnCustomerLogin();
        customerPage.selectUserByIndex(managerPage.customerIndex()-1);
        customerPage.clickOnLogin();
        Assert.assertTrue(customerPage.validateWelcomeMsgIsDisplayed());

    }
    @Test(description = "Validate user can create deposit", dependsOnMethods = "validateCustomerAdded")
    public void validateCustomerDeposit() {
        customerPage.clickOnDeposit();
        customerPage.setAmount("100");
        customerPage.clickOnSubmit();
        Assert.assertEquals(customerPage.validateSuccessMsg(),"Deposit Successful");
    }
    @Test(description = "Validate user can transactions added", dependsOnMethods = "validateCustomerDeposit")
    public void validateCustomerTransactionsAdded() {
        customerPage.clickOnTransactionsBtn();
        Assert.assertTrue(customerPage.validateTransactionAdded());
    }



    @DataProvider(name = "CustomersData")
    public Object[][] readFromExcel() {
        ExcelFileManager excel = new ExcelFileManager(new File("src\\test\\resources\\TestData.xlsx"));
        excel.switchToSheet("NewCustomer");
        Locator = new Object[2][3];
        for (int i = 2; i < 4; i++) {
            Locator[i - 2][0] = excel.getCellData("fName", i);
            Locator[i - 2][1] = excel.getCellData("lName", i);
            Locator[i - 2][2] = excel.getCellData("postCode", i);
        }
        return Locator;
    }
}