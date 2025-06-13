import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * REFERENCE
 * https://www.w3schools.com/java/ref_string_format.asp
 */

/*
* AUTHOR: Foo
* For centralize the data for whole program (import csv data and update csv data for pet and pet owner)
* CREATED: 11/06/2025
* MODIFIED: 13/06/2025
*/

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

  /* ----------------CUSTOMER / PET OWNER---------------- */

  // getter: return the whole customers array list
  public List<PetOwner> getCustomers () {
    return customers;
  }

  // Getter: counter for number of total customers
  public int getCustomerSize () {
    return customers.size();
  }

  // Getter: return all the customer for their name
  public String[] getCustomersNameList () {
    String[] customersName = new String[this.getCustomerSize()];
    for (int i = 0; i < this.getCustomerSize(); i++) {
      customersName[i] = customers.get(i).getPetOwnerName();
    }
    return customersName;
  }

  /* Used in PetPage for creating new Pet
  * IMPORT: index (index for Customers array)
  */
  public String getCustomerID (int index) {
    PetOwner customer = customers.get(index);

    return customer.getPetOwnerID();
  }

  /* getter: return the specific customer name
  * Used in Treatment transaction part
  * IMPORT: index (index for Customers array)
  */
  public String getCustomerName (int index) {
    String customerID = pets.get(index).getPetOwnerID();

    for (PetOwner customer : customers) {
      if (customer.getPetOwnerID().equals(customerID)) {
        return customer.getPetOwnerName();
      }
    }
      return "-";
  }

  /* getter: return the specific customer name
  * Used in PetPage
  * INPORT: customerID (String) 
  */
  public String getCustomerName (String customerID) {
    for (PetOwner customer : customers) {
      if (customer.getPetOwnerID().equals(customerID)) {
        return customer.getPetOwnerName();
      }
    }
    return "-";
  }

  // save all the customers data change to csv file
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

  // method for create new customer
  public void createCustomer (String petOwnerID, String petOwnerName, int petOwnerContact) {
    customers.add(new PetOwner(petOwnerID, petOwnerName, petOwnerContact));
    saveCustomers();
    Toast.showToast(petOwnerName + " Created!", 2000);
  }

  // method for removing customer
  public void removeCustomer (PetOwner customer) {
    String customerName = customer.getPetOwnerName();
    customers.remove(customer);
    saveCustomers();
    Toast.showToast(customerName + " Removed!", 2000);
  }

  // method modifier for customer name
  public void modifyCustomerDetails (String name, PetOwner customer) {
    customer.setPetOwnerName(name);
    saveCustomers();
    Toast.showToast("All changed saved!", 2000);
  }

  // method modifier for customer contact
  public void modifyCustomerDetails (int contact, PetOwner customer) {
    customer.setPetOwnerContact(contact);
    saveCustomers();
    Toast.showToast("All changed saved!", 2000);
  }
  /* ----------------END | CUSTOMER / PET OWNER---------------- */

  /* ----------------PET---------------- */

  // Getter: return all the pets as array list
  public List<Pet> getPets() {
    return pets;
  }

  // Getter: counter for number of total pets
  public int getPetSize () {
    return pets.size();
  }
  
  // Getter: return all the pet for their name
  public String[] getPetsNameList () {
    String[] petsName = new String[this.getPetSize()];
    for (int i = 0; i < this.getPetSize(); i++) {
      petsName[i] = pets.get(i).getPetName();
    }
    return petsName;
  }

  // Pets, update all the changes to the csv file 
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

  // method: create new Pet
  public void createPet(String petID, String petName, String petSpecies, String petBreed, int petAge, String petOwnerID) {
    pets.add(new Pet(petID, petName, petSpecies, petBreed, petAge, petOwnerID));
    savePets(); // save to CSV
    Toast.showToast(petName + " Created!", 2000);
  }

  // method: remove pet
  public void removePet(Pet pet) {
    String petName = pet.getPetName();
    pets.remove(pet);
    savePets(); // save to CSV
    Toast.showToast(petName + " Removed!", 2000);
  }

  // Pet modifier
  public void modifyPet(String petName, String petSpecies, String petBreed, int petAge, Pet pet) {
    pet.setPetName(petName);
    pet.setPetSpecies(petSpecies);
    pet.setPetBreed(petBreed);
    pet.setPetAge(petAge);
    savePets(); // save to CSV
    Toast.showToast("All changed saved!", 2000);
  }

  // initialize for all the Data (from CSV file)
  public void init () {
    this.importPets();
    this.importCustomers();
  }
}
