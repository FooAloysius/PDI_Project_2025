import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

  public String getCustomerName (int index) {
    String customerID = pets.get(index).getPetOwnerID();

    for (PetOwner customer : customers) {
      if (customer.getPetOwnerID().equals(customerID)) {
        return customer.getPetOwnerName();
      }
    }
      return "-";
  }

  public void saveCustomers() {
    String filePath = CSVPath + CustomersFileName;

    try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
      for (PetOwner customer : customers) {
        String line = String.format("%s,%s,%d",
          customer.getPetOwnerID(),
          customer.getPetOwnerName(),
          customer.getPetOwnerContact());
        pw.println(line);
      }
    } catch (IOException e) {
      System.out.println("Error saving customer data: " + e.getMessage());
    }
  }

  public void createCustomer (String petOwnerID, String petOwnerName, int petOwnerContact) {
    customers.add(new PetOwner(petOwnerID, petOwnerName, petOwnerContact));
  }

  public void removeCustomer (PetOwner customer) {
    customers.remove(customer);
    saveCustomers();
  }

  // customer name
  public void modifyCustomerDetails (String name, PetOwner customer) {
    customer.setPetOwnerName(name);
    saveCustomers();
  }

  // customer contact
  public void modifyCustomerDetails (int contact, PetOwner customer) {
    customer.setPetOwnerContact(contact);
    saveCustomers();
  }

  // 保存所有宠物数据到 CSV 文件
  public void savePets() {
    String filePath = CSVPath + PetsFileName;

    try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
      for (Pet pet : pets) {
        String line = String.format("%s,%s,%s,%s,%d,%s",
          pet.getPetID(),
          pet.getPetName(),
          pet.getPetSpecies(),
          pet.getPetBreed(),
          pet.getPetAge(),
          pet.getPetOwnerID());
        pw.println(line);
      }
    } catch (IOException e) {
      System.out.println("Error saving pet data: " + e.getMessage());
    }
  }

  // 添加新的宠物记录
  public void createPet(String petID, String petName, String petSpecies, String petBreed, int petAge, String petOwnerID) {
    pets.add(new Pet(petID, petName, petSpecies, petBreed, petAge, petOwnerID));
    savePets(); // 保存至 CSV 文件
  }

  // 删除宠物记录
  public void removePet(Pet pet) {
    pets.remove(pet);
    savePets(); // 同样保存更改
  }

  // 修改宠物信息（单个字段示例，也可以合并所有字段修改）
  public void updatePet(Pet petToUpdate, String petName, String petSpecies, String petBreed, int petAge) {
    petToUpdate.setPetName(petName);
    petToUpdate.setPetSpecies(petSpecies);
    petToUpdate.setPetBreed(petBreed);
    petToUpdate.setPetAge(petAge);
    savePets();
  }

  public List<Pet> getPets() {
    return pets;
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
