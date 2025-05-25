/*
* AUTHOR: Foo
* CREATED: 15/05/2025
* MODIFIED: 25/05/2025
*/
public class Pet {
  
  // Class Feild
  private String petID;
  private String petName;
  private String petSpecies;
  private String petBreed;
  private int petAge;
  private String petOwnerID;

  // Accessor
  public String getPetID () {
    return this.petID;
  }

  public String getPetName () {
    return this.petName;
  }

  public String getPetSpecies () {
    return this.petSpecies;
  }

  public String getPetBreed () {
    return this.petBreed;
  }
  
  public int getPetAge () {
    return this.petAge;
  }

  public String getPetOwnerID () {
    return this.petOwnerID;
  }

  // Mutator
  public void setPetID (String pPetID) {
    this.petID = pPetID;
  }

  public void setPetName (String pPetName) {
    this.petName = pPetName;
  }

  public void setPetSpecies (String pPetSpecies) {
    this.petSpecies = pPetSpecies;
  }

  public void setPetBreed (String pPetBreed) {
    this.petBreed = pPetBreed;
  }

  public void setPetAge (int pPetAge) {
    this.petAge = pPetAge;
  }

  public void setPetOwnerID (String pPetOwnerID) {
    this.petOwnerID = pPetOwnerID;
  }
  /* END OF MUTATOR */

  /*
  * DEFAULT CONSTRUCTOR
  * ASSERTION: Initialize default pet details
  */
  public Pet () {
    petID = "pet_123456";
    petName = "Kitty";
    petSpecies = "cat";
    petBreed = "Siamese cat";
    petAge = 40;
    petOwnerID = "Customer_123456";
  }

  /* Parameter Constructor */
  public Pet (String pPetID, String pPetName, String pPetSpecies, String pPetBreed, int pPetAge, String pPetOwnerID) {
    petID = pPetID;
    petName = pPetName;
    petSpecies = pPetSpecies;
    petBreed = pPetBreed;
    petAge = pPetAge;
    petOwnerID = pPetOwnerID;
  }

  /* Copy Constructor */
  public Pet (Pet pPet) {
    petID = pPet.getPetID();
    petName = pPet.getPetName();
    petSpecies = pPet.getPetSpecies();
    petBreed = pPet.getPetBreed();
    petAge = pPet.getPetAge();
    petOwnerID = pPet.getPetOwnerID();
  }

  
}
