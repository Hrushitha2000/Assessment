package Final;
import org.json.JSONObject;
import org.json.XML;
import java.io.*;

public class XmlToJsonConverter {
    public static void main(String[] args) {
        
        String xmlPath = "C:\\soft\\input.xml";  
        
        String jsonPath = "C:\\soft\\output.json"; 

        try {
            
            String xmlData = readXmlFile(xmlPath);

            JSONObject jsonObject = XML.toJSONObject(xmlData);

            
            saveJsonToFile(jsonObject, jsonPath);

            System.out.println("JSON file saved successfully at: " + jsonPath);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    private static String readXmlFile(String filePath) throws Exception {
        StringBuilder xmlContent = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            xmlContent.append(line).append("\n");
        }
        br.close();
        return xmlContent.toString();
    }

    
    private static void saveJsonToFile(JSONObject jsonObject, String filePath) throws Exception {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toString(4)); 
        }
    }
}



