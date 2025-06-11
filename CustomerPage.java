
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

public class CustomerPage {
  public JPanel customerList () {
    List<PetOwner> customers= new Data().getCustomers();

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
        System.out.println("Delete " + customer.getPetOwnerName());
      });
      
      listElement.add(nameLabel);
      listElement.add(trashBinButton);
      listPanel.add(Box.createVerticalStrut(5));
      listPanel.add(listElement);
      
    }

    return listPanel;
  }

  public void gui (JPanel panel) {
    panel.removeAll();

    JPanel customerList = customerList();

    panel.add(customerList);
    panel.revalidate();
    panel.repaint();
  } 
  
}
