public class PetOwner {
  private int petOwnerID;
  private String petOwnerName;
  private int petOwnerContact;
  private static Pet[] petsBelongTo = new Pet[];

  /*
  * ACCESSOR: getPetOwnerID
  * IMPORT: none
  * EXPORT: petOwnerID (int)
  */
  public int getPetOwnerID () {
    return this.petOwnerID;
  }

  /*
  * ACCESSOR: getPetOwnerName
  * IMPORT: none
  * EXPORT: petOwnerName (String)
  */
  public String getPetOwnerName() {
    return this.petOwnerName;
  }

  /*
  * ACCESSOR: getPetOwnerContact
  * IMPORT: none
  * EXPORT: petOwnerContact (int)
  */
  public int getPetOwnerContact() {
    return this.petOwnerContact;
  }

  /*
  * MUTATOR: setPetOwnerID
  * IMPORT: pPetOwnerID (int)
  * EXPORT: none
  */
  public void setPetOwnerID (int pPetOwnerID) {
    this.petOwnerID = pPetOwnerID;
  }

  /*
  * MUTATOR: setPetOwnerName
  * IMPORT: pPetOwnerName
  * EXPORT: none
  */
  public void setPetOwnerName (String pPetOwnerName) {
    this.petOwnerName = pPetOwnerName;
  }

  /*
   * MUTATOR: setPetOwnerContact
   * IMPORT: pPetOwnerContact
   * EXPORT: none
  */
  public void setPetOwnerContact (int pPetOwnerContact) {
    this.petOwnerContact = pPetOwnerContact;
  }

  /*
  * DEFAULT CONSTRUCTOR
  * CONSTRUCTOR: PetOwner
  * IMPORT: none
  * EXPORT: none
  * ASSERTION: Initialize default pet owner details
  */
  public PetOwner () {
    petOwnerID = 1234567;
    petOwnerName = "Abang";
    petOwnerContact = 0121111111;
    petsBelongTo = {};
  }

  /*
  * PARAMETER CONSTRUCTOR
  * CONSTRUCTOR: PetOwner
  * IMPORT: pPetOwnerID (int), pPetOwnerName (String), pPetOwnerContact (int)
  * EXPORT: none
  * ASSERTION: Constructor with parameter
  */
  public PetOwner (int pPetOwnerID, String pPetOwnerName, int pPetOwnerContact) {
    petOwnerID = pPetOwnerID;
    petOwnerName = pPetOwnerName;
    petOwnerContact = pPetOwnerContact;
  }

  /*
  * COPY CONSTRUCTOR
  * CONSTRUCTOR: PetOwner
  * IMPORT: PetOwner (COPY FROM PetOwner)
  * EXPORT: none
  * ASSERTION: Constructor with parameter
  */
  public PetOwner (PetOwner pPetOwner) {
    petOwnerID = pPetOwner.getPetOwnerID();
    petOwnerName = pPetOwner.getPetOwnerName();
    petOwnerContact = pPetOwner.getPetOwnerContact();
  }

}
