package FileWrappers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public  class ReadOrWriteData {
public static String[] Read_From_JsonFile()
{
    JSONParser jsonParser = new JSONParser();
    try {
        FileReader fileReader = new FileReader("E:\\testing\\TA Ahmed reda\\Levelset\\Dataset.json");
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject)obj;
        JSONArray jsonArray =(JSONArray) jsonObject.get("DocumentLocater");
        String Elements[] = new String[jsonArray.size()];
        for (int i=0;i<jsonArray.size();i++)
        {
            JSONObject Element = (JSONObject)jsonArray.get(i);
            Elements[i] = (String)Element.get("Elemet");
        }
        return  Elements;
    }
    catch (IOException | ParseException e) {
        e.printStackTrace();
    }
    return null;
}
}
