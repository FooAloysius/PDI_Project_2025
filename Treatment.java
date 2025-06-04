import java.io.*;
import java.util.*;

public class Treatment {
    // Fields
    private String treatmentID;
    private String treatmentName;
    private double treatmentFee;

    // 1. Default Constructor
    public Treatment() {
        this.treatmentID = "";
        this.treatmentName = "";
        this.treatmentFee = 0.0;
    }

    // 2. Parameterized Constructor
    public Treatment(String id, String name, double fee) {
        setTreatmentID(id);
        setTreatmentName(name);
        setTreatmentFee(fee);
    }

    // 3. Copy Constructor
    public Treatment(Treatment copy) {
        this.treatmentID = copy.getTreatmentID();
        this.treatmentName = copy.getTreatmentName();
        this.treatmentFee = copy.getTreatmentFee();
    }

    // Getter and Setter for treatmentID
    public String getTreatmentID() {
        return treatmentID;
    }

    public void setTreatmentID(String id) {
        if (id != null && !id.isEmpty()) {
            this.treatmentID = id;
        }
    }

    // Getter and Setter for treatmentName
    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String name) {
        if (name != null && !name.isEmpty()) {
            this.treatmentName = name;
        }
    }

    // Getter and Setter for treatmentFee
    public double getTreatmentFee() {
        return treatmentFee;
    }

    public void setTreatmentFee(double fee) {
        if (fee >= 0.0) {
            this.treatmentFee = fee;
        }
    }

    // Static method to read treatments from CSV file
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

                    Treatment t = new Treatment(id, name, fee);
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

    // Optional: helpful for displaying in combo box or logs
    @Override
    public String toString() {
        return treatmentName + " (RM " + String.format("%.2f", treatmentFee) + ")";
    }
}
