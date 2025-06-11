
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
/*
 * https://www.tutorialspoint.com/awt/awt_focusadapter.htm
 * 
 * 
 */

public class CustomerPage {
  JPanel mainPanel;
  JPanel modal;
  JPanel customerList;
  JPanel customerCreate;
  Color LIGHTBEACH = new Color(255, 254, 236);
  Color WHITE = new Color(255, 254, 242);

  public JPanel customerList (Data data) {
    List<PetOwner> customers= data.getCustomers();

    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); // layout for list
    listPanel.setBackground(WHITE);

    for (PetOwner customer: customers) {
      JPanel listElement = new JPanel();
      listElement.setLayout(new BoxLayout(listElement, BoxLayout.Y_AXIS));
      listElement.setBackground(LIGHTBEACH);
      listElement.setPreferredSize(new Dimension(400, 30));
      listElement.setAlignmentX(Component.CENTER_ALIGNMENT);

      listElement.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          System.out.println("Clicked div " + customer.getPetOwnerName());
        }
      });
      
      JLabel nameLabel = new JLabel(customer.getPetOwnerName());
      nameLabel.setBackground(LIGHTBEACH);

      JPanel actionBox = new JPanel();
      actionBox.setLayout(new BoxLayout(actionBox, BoxLayout.X_AXIS));
      actionBox.setOpaque(true);

      // Image Icon for trash bin 
      ImageIcon trashBinIcon = new ImageIcon(((new ImageIcon("./icons/trash_icon_229px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
      ImageIcon trashBinIconHover = new ImageIcon(((new ImageIcon("./icons/trash_icon_hover_229px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)); // hover icon
      // button to pet panel
      JButton trashBinButton = new JButton("Remove customer", trashBinIcon);
      trashBinButton.setBorderPainted(false); // set border to none
      trashBinButton.setBackground(LIGHTBEACH);
      trashBinButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
      trashBinButton.setFocusable(false);
      trashBinButton.setVerticalTextPosition(JButton.BOTTOM);
      trashBinButton.setHorizontalTextPosition(JButton.CENTER);
      trashBinButton.setRolloverIcon(trashBinIconHover);
      trashBinButton.addActionListener((actionEvent) -> {
        data.removeCustomer(customer);
        mainPanel.removeAll();
        mainPanel.add(customerList(data));
        mainPanel.revalidate();
        mainPanel.repaint();
      });

      // Image Icon for modifier
      ImageIcon modifierIcon = new ImageIcon(((new ImageIcon("./icons/pencil_icon_200px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
      ImageIcon modifierIconHover = new ImageIcon(((new ImageIcon("./icons/pencil_icon_hover_200px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)); // hover icon
      // button to pet panel
      JButton modifierButton = new JButton("Modify details", modifierIcon);
      modifierButton.setBorderPainted(false); // set border to none
      modifierButton.setBackground(LIGHTBEACH);
      modifierButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
      modifierButton.setFocusable(false);
      modifierButton.setVerticalTextPosition(JButton.BOTTOM);
      modifierButton.setHorizontalTextPosition(JButton.CENTER);
      modifierButton.setRolloverIcon(modifierIconHover);
      modifierButton.addActionListener((actionEvent) -> {
        modal = customerModify(customer, data);
        mainPanel.removeAll();
        mainPanel.add(modal);
        mainPanel.revalidate();
        mainPanel.repaint();
      });
      
      actionBox.add(trashBinButton);
      actionBox.add(modifierButton);
      listElement.add(nameLabel);
      listElement.add(actionBox);
      listPanel.add(Box.createVerticalStrut(5));
      listPanel.add(listElement);
      
    }
    
    return listPanel;
  }
  
  public JPanel customerModify (PetOwner customer, Data data) {
    JPanel customerModifyModal = new JPanel();
    customerModifyModal.setLayout(new BoxLayout(customerModifyModal, BoxLayout.Y_AXIS));
    customerModifyModal.setPreferredSize(new Dimension(400,300));
    
    // customer id
    JPanel idGroup = new JPanel();
    JLabel idLabel = new JLabel("Customer ID: ");
    JLabel idInput = new JLabel(customer.getPetOwnerID());
    idInput.setPreferredSize(new Dimension(250,40));
    idGroup.add(idLabel);
    idGroup.add(idInput);

    // customer name
    JPanel nameGroup = new JPanel();
    JLabel nameLabel = new JLabel("Customer Name: ");
    JTextField nameInput = new JTextField(customer.getPetOwnerName());
    nameInput.setPreferredSize(new Dimension(250,40));
    nameGroup.add(nameLabel);
    nameGroup.add(nameInput);
    nameInput.addFocusListener(new FocusAdapter() {
      public void focusLost (FocusEvent e) {
        data.modifyCustomerDetails(nameInput.getText(), customer);
      }
    });
    
    // customer contact
    JPanel contactGroup = new JPanel();
    JLabel contactLabel = new JLabel("Customer contact: ");
    JTextField contactInput = new JTextField(Integer.toString(customer.getPetOwnerContact()));
    contactInput.setPreferredSize(new Dimension(250,40));
    contactGroup.add(contactLabel);
    contactGroup.add(contactInput);
    contactInput.addFocusListener(new FocusAdapter() {
      public void focusLost (FocusEvent e) {
        data.modifyCustomerDetails(Integer.parseInt(contactInput.getText()), customer);
      }
    });

    // back to customer List
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
      gui(data);
    });
    
    customerModifyModal.add(idGroup);
    customerModifyModal.add(Box.createVerticalStrut(20));
    customerModifyModal.add(nameGroup);
    customerModifyModal.add(contactGroup);
    customerModifyModal.add(backButton);

    return customerModifyModal;
  }

  public JPanel customerCreate (Data data) {
    JPanel customerModifyModal = new JPanel();
    customerModifyModal.setLayout(new BoxLayout(customerModifyModal, BoxLayout.Y_AXIS));
    customerModifyModal.setPreferredSize(new Dimension(400,300));
    
    // customer id
    JPanel idGroup = new JPanel();
    JLabel idLabel = new JLabel("Customer ID: ");
    int randomNumber = (int) (Math.random() * 1_000_000);
    String customerID = String.format("Customer_%06d", randomNumber); // fix to 6 digit

    JLabel idInput = new JLabel(customerID);
    idInput.setPreferredSize(new Dimension(250,40));
    idGroup.add(idLabel);
    idGroup.add(idInput);

    // customer name
    JPanel nameGroup = new JPanel();
    JLabel nameLabel = new JLabel("Customer Name: ");
    JTextField nameInput = new JTextField();
    nameInput.setPreferredSize(new Dimension(250,40));
    nameGroup.add(nameLabel);
    nameGroup.add(nameInput);
    
    // customer contact
    JPanel contactGroup = new JPanel();
    JLabel contactLabel = new JLabel("Customer contact: ");
    JTextField contactInput = new JTextField();
    contactInput.setPreferredSize(new Dimension(250,40));
    contactGroup.add(contactLabel);
    contactGroup.add(contactInput);

    // create to customer List
    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      data.createCustomer(customerID, nameInput.getText(), Integer.parseInt(contactInput.getText()));
      gui(data);
    });

    // back to customer List
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
      gui(data);
    });
    
    customerModifyModal.add(idGroup);
    customerModifyModal.add(Box.createVerticalStrut(20));
    customerModifyModal.add(nameGroup);
    customerModifyModal.add(contactGroup);
    customerModifyModal.add(backButton);
    customerModifyModal.add(createButton);

    return customerModifyModal;
  }

  public void gui (JPanel panel, Data data) {
    mainPanel = panel;
    panel.removeAll();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    customerList = customerList(data);
    JButton createCustomerBtn = new JButton("Create New Customer");
    createCustomerBtn.addActionListener(e -> {
      mainPanel.removeAll();
      customerCreate = customerCreate(data);
      mainPanel.add(customerCreate);
      mainPanel.revalidate();
      mainPanel.repaint();
    });

    panel.add(customerList);
    panel.add(createCustomerBtn);
    panel.revalidate();
    panel.repaint();
  } 

  public void gui (Data data) {
    mainPanel.removeAll();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    customerList = customerList(data);
    JButton createCustomerBtn = new JButton("Create New Customer");
    createCustomerBtn.addActionListener(e -> {
      mainPanel.removeAll();
      customerCreate = customerCreate(data);
      mainPanel.add(customerCreate);
      mainPanel.revalidate();
      mainPanel.repaint();
    });

    mainPanel.add(customerList);
    mainPanel.add(createCustomerBtn);
    mainPanel.revalidate();
    mainPanel.repaint();
  } 
  
}
