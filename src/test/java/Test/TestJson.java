package Test;

import utilities.readers.ReadDataFromJSonFile;

import org.testng.Assert;
import org.testng.annotations.*;
public class TestJson {
    String Actual;


   @Test
    public void TestgetValueOfNode()
    {
        Actual= new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPath).getValueOfNode("HomePageLocaters/creatDocument");
        Assert.assertEquals(Actual,"Create a Document");

    }
    @Test
    public void TestgetValuesOfNode()
    {
        Actual= new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPath).getValuesOf("DocumentPage",null).get(0);
        Assert.assertEquals(Actual,"//div[text()='Notice of Intent to Lien']/..//span[text()='Free']");

    }


}
