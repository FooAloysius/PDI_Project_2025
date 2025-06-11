/* REFERENCE
 * https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
 * https://www.baeldung.com/java-datetimeformatter
 * https://docs.oracle.com/cd/E84527_01/wcs/tag-ref/MISC/TimeZones.html
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/*
* AUTHOR: Foo
* CREATED: 11/06/2025
* MODIFIED: 11/06/2025
*/
public class HomePage {
  private JTextField nameField;
  private JTextField emailField;
  private JTextField petsField;
  CustomerPage customerPage = new CustomerPage();
  PetPage petPage = new PetPage();

  JLabel labelDateTime;
  public void gui (JPanel panel, Data data) {
    panel.removeAll();

    JPanel homePanel = new JPanel();
    homePanel.setBackground(Color.WHITE);
    homePanel.setLayout(new FlowLayout());

    JPanel topLabelPanel = new JPanel();

    JLabel pageTitle = new JLabel("ANIMAL CLINIC");
    pageTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
    topLabelPanel.add(pageTitle);
    
    showLocalDateTime();
    topLabelPanel.add(labelDateTime);
    homePanel.add(topLabelPanel);

    JPanel petNavPanel = new JPanel();

    // Image Icon for pet view
    ImageIcon petViewIcon = new ImageIcon(((new ImageIcon("./images/pet_image_1280px.png")).getImage()).getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
    ImageIcon petViewIconHover = new ImageIcon(((new ImageIcon("./images/pet_image_hover_1280px.png")).getImage()).getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
    // button to pet panel
    JButton petViewButton = new JButton("View Pets", petViewIcon);
    petViewButton.setContentAreaFilled(false); // transparent background
    petViewButton.setBorderPainted(false); // set border to none
    petViewButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    petViewButton.setFocusable(false);
    petViewButton.setVerticalTextPosition(JButton.BOTTOM);
    petViewButton.setHorizontalTextPosition(JButton.CENTER);
    petViewButton.setRolloverIcon(petViewIconHover);
    petViewButton.addActionListener((actionEvent) -> {
      petPage.gui(panel, data);
    });

    // Image Icon for pet view
    ImageIcon customerViewIcon = new ImageIcon(((new ImageIcon("./images/customer_image_900px.png")).getImage()).getScaledInstance(220, 300, java.awt.Image.SCALE_SMOOTH));
    ImageIcon customerViewIconHover = new ImageIcon(((new ImageIcon("./images/customer_image_hover_900px.png")).getImage()).getScaledInstance(220, 300, java.awt.Image.SCALE_SMOOTH)); // hover icon
    // button to pet panel
    JButton customerViewButton = new JButton("View Customers", customerViewIcon);
    customerViewButton.setContentAreaFilled(false); // transparent background
    customerViewButton.setBorderPainted(false); // set border to none
    customerViewButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    customerViewButton.setFocusable(false);
    customerViewButton.setVerticalTextPosition(JButton.BOTTOM);
    customerViewButton.setHorizontalTextPosition(JButton.CENTER);
    customerViewButton.setRolloverIcon(customerViewIconHover);
    customerViewButton.addActionListener((actionEvent) -> {
      customerPage.gui(panel, data);
    });

    petNavPanel.add(petViewButton);
    petNavPanel.add(customerViewButton);
    
    homePanel.add(petNavPanel);


    /* Big Panel Setting start here */

    panel.add(homePanel);
    panel.revalidate();
    panel.repaint();

    /* Ended Big Panel Setting */
  } 

  public void showLocalDateTime () {
    labelDateTime = new JLabel();
    Timer timer = new Timer(1000,  new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LocalDateTime now = LocalDateTime.now();
            labelDateTime.setText(
              now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            );
        }
    });
    timer.setInitialDelay(0);
    timer.start();
  }


}
