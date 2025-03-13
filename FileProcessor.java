package Final;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file path (Excel .xlsx or CSV): ");
        String file = sc.nextLine();

        System.out.println("Enter the starting row number (1 for the first row): ");
        int startRow = sc.nextInt();

        try {
            if (file.endsWith(".xlsx") || file.endsWith(".csv")) {
                processExcelFile(file, startRow);
            } else {
                System.out.println("Unsupported file type. Please provide an Excel (.xls) or CSV file.");
            }
        } catch (IOException e) {
            System.out.println("Error processing the file: " + e.getMessage());
        }
    }

    private static void processExcelFile(String filePath, int startRow) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] data = new byte[1024];
        int bytesRead;
        int currentRow = 0;
        while ((bytesRead = fis.read(data)) != -1) {
            String line = new String(data, 0, bytesRead);
            String[] values = line.split("\t"); // This is a naive approach

            if (currentRow >= startRow - 1) {
                for (String value : values) {
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
            currentRow++;
        }
        fis.close();
    }

    private static void processCSVFile(String filePath, int startRow) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentRow = 0;

            while ((line = br.readLine()) != null) {
                currentRow++;
                if (currentRow >= startRow) {
                    String[] values = line.split(","); 
                    for (String value : values) {
                        System.out.print(value + "\t");
                    }
                    System.out.println();
                }
            }
        }
    }
}


