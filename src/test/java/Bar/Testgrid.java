package Bar;

import BasePackage.WebActionsForElement;
import FileWrappers.ExcelFileManager;
import Home.HomePage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class Testgrid extends TestBaseGrid {

    String Actual;
    String lienBondlaim = "'Lien / Bond Claim'";



    @Test
    public void CheckfreeText()
    {
        Actual = "Free";
        Assert.assertEquals("Free",Actual);
    }




}
