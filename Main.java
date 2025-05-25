import java.io.*;

public class Main {
  private static String CSVPath = "csv/";
  private static String PetsFileName = "Pets.csv";
  private static String CustomersFileName = "Customers.csv";
  private static String TreatmentFileName = "Treatments.csv";
  private static Pet[] pets =new Pet[40];

  private static int petCount = 0;

  public static void importPets() {
    String PetFilePath = CSVPath + PetsFileName;

    petCount = 0;

    try(BufferedReader br = new BufferedReader(new FileReader(PetFilePath))) {
      String line;
      while((line = br.readLine()) != null) {
        if(line.trim().isEmpty() || line.contains(" ,") || line.contains(", ") || !line.contains(",")) {
          continue; // invalid line
        }

        String[] parts = line.split(",");
        if(parts.length != 6) {
          continue; //invalid
        }

        try {
          String petID = parts[0].trim();
          String petName = parts[1].trim();
          String petSpecies = parts[2].trim();
          String petBreed = parts[3].trim();
          int petAge = Integer.parseInt(parts[4].trim());
          String petOwnerID = parts[5].trim();
          pets[petCount++]= new Pet(petID, petName, petSpecies, petBreed, petAge, petOwnerID);
          
        } catch (NumberFormatException e) {
          // 
          System.out.println(e);
        }
      }
    }catch(IOException e){
        System.out.println("File error:"+ e.getMessage());
    }
  }

  public static void main(String[] args) {
    importPets();
    System.out.println(pets[3].getPetSpecies());
  }
}
