
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerPage {
  JPanel mainPanel;
  JPanel modal;
  public JPanel customerList (Data data) {
    List<PetOwner> customers= data.getCustomers();

    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); // layout for list

    for (PetOwner customer: customers) {
      JPanel listElement = new JPanel();
      listElement.setLayout(new BoxLayout(listElement, BoxLayout.X_AXIS));
      listElement.setBackground(Color.LIGHT_GRAY);
      listElement.setPreferredSize(new Dimension(400, 30));
      listElement.setAlignmentX(Component.LEFT_ALIGNMENT);

      listElement.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          System.out.println("Clicked div " + customer.getPetOwnerName());
        }
      });
      
      JLabel nameLabel = new JLabel(customer.getPetOwnerName());

      // Image Icon for trash bin 
      ImageIcon trashBinIcon = new ImageIcon(((new ImageIcon("./icons/trash_icon_229px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
      ImageIcon trashBinIconHover = new ImageIcon(((new ImageIcon("./icons/trash_icon_hover_229px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)); // hover icon
      // button to pet panel
      JButton trashBinButton = new JButton("Remove user", trashBinIcon);
      trashBinButton.setContentAreaFilled(false); // transparent background
      trashBinButton.setBorderPainted(false); // set border to none
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
      modifierButton.setContentAreaFilled(false); // transparent background
      modifierButton.setBorderPainted(false); // set border to none
      modifierButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
      modifierButton.setFocusable(false);
      modifierButton.setVerticalTextPosition(JButton.BOTTOM);
      modifierButton.setHorizontalTextPosition(JButton.CENTER);
      modifierButton.setRolloverIcon(modifierIconHover);
      modifierButton.addActionListener((actionEvent) -> {
        modal = customerModify(customer);
        mainPanel.removeAll();
        mainPanel.add(modal);
        mainPanel.revalidate();
        mainPanel.repaint();
      });
      
      listElement.add(nameLabel);
      listElement.add(trashBinButton);
      listElement.add(modifierButton);
      listPanel.add(Box.createVerticalStrut(5));
      listPanel.add(listElement);
      
    }
    
    return listPanel;
  }
  
  public JPanel customerModify (PetOwner customer) {
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
    
    // customer contact
    JPanel contactGroup = new JPanel();
    JLabel contactLabel = new JLabel("Customer contact: ");
    JTextField contactInput = new JTextField(Integer.toString(customer.getPetOwnerContact()));
    contactInput.setPreferredSize(new Dimension(250,40));
    contactGroup.add(contactLabel);
    contactGroup.add(contactInput);
    
    customerModifyModal.add(idGroup);
    customerModifyModal.add(Box.createVerticalStrut(20));
    customerModifyModal.add(nameGroup);
    // customerModifyModal.add(Box.createRigidArea(new Dimension(0,30)));
    customerModifyModal.add(contactGroup);

    return customerModifyModal;
  }

  public void gui (JPanel panel, Data data) {
    mainPanel = panel;
    panel.removeAll();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JPanel customerList = customerList(data);

    panel.add(customerList);
    panel.revalidate();
    panel.repaint();
  } 
  
}
