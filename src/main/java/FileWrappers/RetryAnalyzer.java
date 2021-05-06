package FileWrappers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int counter =0;
    int RetryLimit =  Integer.parseInt( ReadFromPropertiesFile.getValue("RetryLimit"));
    @Override
    public boolean retry(ITestResult result) {
        if(counter <RetryLimit)
        {
            counter++;
            return true;
        }
        return false;
    }
}
