public class PetOwner {
  private int petOwnerID;
  private String petOwnerName;
  private int petOwnerContact;

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

}
