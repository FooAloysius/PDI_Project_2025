import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {
  public static String CSVPath = "csv/";
  public static String PetsFileName = "Pets.csv";
  public static String CustomersFileName = "Customers.csv";
  public static String TreatmentFileName = "Treatments.csv";
  static List<Pet> pets = new ArrayList<>();
  static List<PetOwner> customers = new ArrayList<>();

  public static void importPets() {
    String PetFilePath = CSVPath + PetsFileName;

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
          // how to resize array
          pets.add(new Pet(petID, petName, petSpecies, petBreed, petAge, petOwnerID));
          
        } catch (NumberFormatException e) {
          // 
          System.out.println(e);
        }
      }
    }catch(IOException e){
        System.out.println("File error:"+ e.getMessage());
    }
  }

  public static void importCustomers() {
    String CustomerFilePath = CSVPath + CustomersFileName;

    try(BufferedReader br = new BufferedReader(new FileReader(CustomerFilePath))) {
      String line;
      while((line = br.readLine()) != null) {
        if(line.trim().isEmpty() || line.contains(" ,") || line.contains(", ") || !line.contains(",")) {
          continue; // invalid line
        }

        String[] parts = line.split(",");
        if(parts.length != 3) {
          continue; //invalid
        }

        try {
          String petOwnerID = parts[0].trim();
          String petOwnerName = parts[1].trim();
          int petOwnerContact = Integer.parseInt(parts[2].trim());
          
          customers.add(new PetOwner(petOwnerID, petOwnerName, petOwnerContact));
          
        } catch (NumberFormatException e) {
          // 
          System.out.println(e);
        }
      }
    }catch(IOException e){
        System.out.println("File error:"+ e.getMessage());
    }
  }

  public List<PetOwner> getCustomers () {
    return customers;
  }

  public void removeCustomer (PetOwner customer) {
    customers.remove(customer);
  }

  public int getPetSize () {
    return pets.size();
  }

  public String[] getPetsNameList () {
    String[] petsName = new String[this.getPetSize()];
    for (int i = 0; i < this.getPetSize(); i++) {
      petsName[i] = pets.get(i).getPetName();
    }
    return petsName;
  }

  public void init () {
    this.importPets();
    this.importCustomers();
    System.out.println(pets.get(3).getPetSpecies()); // display pet name
    System.out.println(customers.get(2).getPetOwnerName()); // display Customer name

    String[] pets = new Data().getPetsNameList();

    for (int i = 0; i < pets.length; i++) {
        System.out.println(pets[i]);
    }
  }
}
