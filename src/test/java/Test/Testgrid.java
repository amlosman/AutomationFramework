package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

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
