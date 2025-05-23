import java.io.*;
import java.util.*;

public class Treatment {

    // Static method to read treatments from a CSV file
    public static List<Treatment> loadFromCSV(String filename) {
        List<Treatment> treatmentList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    double fee = Double.parseDouble(parts[2].trim());

                    Treatment t = new Treatment();
                    treatmentList.add(t);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file.");
        }

        return treatmentList;
    }
}
