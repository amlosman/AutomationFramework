package Bar;


import FileWrappers.SendMail;
import org.testng.annotations.Test;

public class trytest {
    @Test
    public void test()
    {
        SendMail.SentTheLog();
    }
}
