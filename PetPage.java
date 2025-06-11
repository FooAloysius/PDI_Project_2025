import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

public class PetPage {
  JPanel mainPanel;
  JPanel modal;
  JPanel petList;
  JPanel petCreate;
  JButton createPetBtn;
  Color LIGHTBEACH = new Color(255, 254, 236);
  Color WHITE = new Color(255, 254, 242);

  public JPanel petList(Data data) {
    List<Pet> pets = data.getPets();

    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    listPanel.setBackground(WHITE);

    for (Pet pet : pets) {
      JPanel listElement = new JPanel();
      listElement.setLayout(new BoxLayout(listElement, BoxLayout.Y_AXIS));
      listElement.setBackground(LIGHTBEACH);
      listElement.setPreferredSize(new Dimension(400, 30));
      listElement.setAlignmentX(Component.CENTER_ALIGNMENT);

      listElement.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          System.out.println("Clicked pet: " + pet.getPetName());
        }
      });

      JLabel nameLabel = new JLabel(pet.getPetName());
      nameLabel.setBackground(LIGHTBEACH);

      JPanel actionBox = new JPanel();
      actionBox.setLayout(new BoxLayout(actionBox, BoxLayout.X_AXIS));
      actionBox.setOpaque(true);

      ImageIcon trashIcon = new ImageIcon(new ImageIcon("./icons/trash_icon_229px.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      ImageIcon trashHover = new ImageIcon(new ImageIcon("./icons/trash_icon_hover_229px.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      JButton trashBtn = new JButton("Remove pet", trashIcon);
      trashBtn.setBorderPainted(false);
      trashBtn.setBackground(LIGHTBEACH);
      trashBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      trashBtn.setFocusable(false);
      trashBtn.setVerticalTextPosition(JButton.BOTTOM);
      trashBtn.setHorizontalTextPosition(JButton.CENTER);
      trashBtn.setRolloverIcon(trashHover);
      trashBtn.addActionListener(e -> {
        data.removePet(pet);
        mainPanel.removeAll();
        mainPanel.add(petList(data));
        mainPanel.revalidate();
        mainPanel.repaint();
      });

      ImageIcon editIcon = new ImageIcon(new ImageIcon("./icons/pencil_icon_200px.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      ImageIcon editHover = new ImageIcon(new ImageIcon("./icons/pencil_icon_hover_200px.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      JButton editBtn = new JButton("Edit pet", editIcon);
      editBtn.setBorderPainted(false);
      editBtn.setBackground(LIGHTBEACH);
      editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      editBtn.setFocusable(false);
      editBtn.setVerticalTextPosition(JButton.BOTTOM);
      editBtn.setHorizontalTextPosition(JButton.CENTER);
      editBtn.setRolloverIcon(editHover);
      editBtn.addActionListener(e -> {
        modal = petModify(pet, data);
        mainPanel.removeAll();
        mainPanel.add(modal);
        mainPanel.revalidate();
        mainPanel.repaint();
      });

      actionBox.add(trashBtn);
      actionBox.add(editBtn);
      listElement.add(nameLabel);
      listElement.add(actionBox);
      listPanel.add(Box.createVerticalStrut(5));
      listPanel.add(listElement);
    }

    return listPanel;
  }

  public JPanel petModify(Pet pet, Data data) {
    JPanel petModifyPanel = new JPanel();
    petModifyPanel.setLayout(new BoxLayout(petModifyPanel, BoxLayout.Y_AXIS));
    petModifyPanel.setPreferredSize(new Dimension(400, 300));

    petModifyPanel.add(makeLabelField("Pet ID:", pet.getPetID()));
    JTextField nameField = makeEditableField("Pet Name:", pet.getPetName());
    JTextField speciesField = makeEditableField("Species:", pet.getPetSpecies());
    JTextField breedField = makeEditableField("Breed:", pet.getPetBreed());
    JTextField ageField = makeEditableField("Age:", Integer.toString(pet.getPetAge()));

    nameField.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
        data.modifyPet(nameField.getText(), speciesField.getText(), breedField.getText(), Integer.parseInt(ageField.getText()), pet);
      }
    });

    speciesField.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
        data.modifyPet(nameField.getText(), speciesField.getText(), breedField.getText(), Integer.parseInt(ageField.getText()), pet);
      }
    });

    breedField.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
        data.modifyPet(nameField.getText(), speciesField.getText(), breedField.getText(), Integer.parseInt(ageField.getText()), pet);
      }
    });

    ageField.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
        data.modifyPet(nameField.getText(), speciesField.getText(), breedField.getText(), Integer.parseInt(ageField.getText()), pet);
      }
    });

    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
      gui(data);
    });

    petModifyPanel.add(nameField.getParent());
    petModifyPanel.add(speciesField.getParent());
    petModifyPanel.add(breedField.getParent());
    petModifyPanel.add(ageField.getParent());
    petModifyPanel.add(makeLabelField("Pet Owner:", data.getCustomerName(pet.getPetOwnerID()))); // pet owner name
    petModifyPanel.add(backButton);

    return petModifyPanel;
  }

  public JPanel petCreate(Data data) {
    JPanel petCreatePanel = new JPanel();
    petCreatePanel.setLayout(new BoxLayout(petCreatePanel, BoxLayout.Y_AXIS));
    petCreatePanel.setPreferredSize(new Dimension(400, 300));

    int randomNumber = (int) (Math.random() * 1_000_000);
    String petID = String.format("Pet_%06d", randomNumber);

    petCreatePanel.add(makeLabelField("Pet ID:", petID));
    JTextField nameField = makeEditableField("Pet Name:", "");
    JTextField speciesField = makeEditableField("Species:", "");
    JTextField breedField = makeEditableField("Breed:", "");
    JTextField ageField = makeEditableField("Age:", "");
    JComboBox ownerComboBox = makeComboBox("Customer Selector:", data.getCustomersNameList());

    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      data.createPet(petID, nameField.getText(), speciesField.getText(), breedField.getText(), Integer.parseInt(ageField.getText()), data.getCustomerID(ownerComboBox.getSelectedIndex()));
      gui(data);
    });

    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
      gui(data);
    });

    petCreatePanel.add(nameField.getParent());
    petCreatePanel.add(speciesField.getParent());
    petCreatePanel.add(breedField.getParent());
    petCreatePanel.add(ageField.getParent());
    petCreatePanel.add(ownerComboBox.getParent());
    petCreatePanel.add(backButton);
    petCreatePanel.add(createButton);

    return petCreatePanel;
  }

  // initialize for gui
  public void gui(JPanel panel, Data data) {
    mainPanel = panel;
    panel.removeAll();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    petList = petList(data);
    createPetBtn = makeCreatePetBtn(data);

    panel.add(petList);
    panel.add(createPetBtn);
    panel.revalidate();
    panel.repaint();
  }

  public void gui(Data data) {
    mainPanel.removeAll();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    petList = petList(data);
    createPetBtn = makeCreatePetBtn(data);

    mainPanel.add(petList);
    mainPanel.add(createPetBtn);
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  // Helper to generate static label panel
  private JPanel makeLabelField(String label, String value) {
    JPanel panel = new JPanel();
    JLabel jLabel = new JLabel(label);
    JLabel jValue = new JLabel(value);
    jValue.setPreferredSize(new Dimension(250, 40));
    panel.add(jLabel);
    panel.add(jValue);
    return panel;
  }

  // Helper to generate editable field with label
  private JTextField makeEditableField(String label, String value) {
    JPanel panel = new JPanel();
    JLabel jLabel = new JLabel(label);
    JTextField jField = new JTextField(value);
    jField.setPreferredSize(new Dimension(250, 40));
    panel.add(jLabel);
    panel.add(jField);
    return jField;
  }

  // Helper to generate editable field with label
  private JComboBox makeComboBox(String label, String[] nameList) {
    JPanel panel = new JPanel();
    JLabel jLabel = new JLabel(label);
    JComboBox jComboBox = new JComboBox<>(nameList);
    jComboBox.setPreferredSize(new Dimension(250, 40));
    panel.add(jLabel);
    panel.add(jComboBox);
    return jComboBox;
  }

  private JButton makeCreatePetBtn(Data data) {
    JButton jButton = new JButton("Add New Pet");
    jButton.addActionListener(e -> {
      mainPanel.removeAll();
      petCreate = petCreate(data);
      mainPanel.add(petCreate);
      mainPanel.revalidate();
      mainPanel.repaint();
    });

    return jButton;
  }
}
