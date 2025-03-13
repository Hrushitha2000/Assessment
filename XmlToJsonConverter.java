package Final;
import org.json.JSONObject;
import org.json.XML;
import java.io.*;
import java.util.Scanner;

public class XmlToJsonConverter {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        
        System.out.println("Enter the path of xml file");
        String xmlPath = sc.nextLine();
        
        System.out.println("Enter the output path for json file");
        String jsonPath = sc.nextLine();

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



