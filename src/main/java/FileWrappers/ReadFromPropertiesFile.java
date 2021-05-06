package FileWrappers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadFromPropertiesFile {
    static String fileNamePath = "E:\\testing\\TA Ahmed reda\\Levelset\\DocomentElements.properties";
    public static Properties  properties = new Properties();

    static {
        FileInputStream fs =null ;
        try {
            fs = new FileInputStream(fileNamePath);
            properties.load(fs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();

        }
        finally {
            try {
                fs.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public static String getValue(String  Key)
    {
        try {
            return properties.getProperty(Key);
        }
        catch (Exception ex)
        {
            ex.getMessage();
            return  null;
        }
    }
}
