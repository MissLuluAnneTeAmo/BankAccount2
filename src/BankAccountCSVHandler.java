import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountCSVHandler {

    public static List<String[]> readCSV(String fileName) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file.");
            e.printStackTrace();
        }
        return data;
    }

    public static void writeCSV(String fileName, List<String[]> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    writer.append(row[i]);
                    if (i < row.length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            System.out.println("Data written to CSV file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }
}
