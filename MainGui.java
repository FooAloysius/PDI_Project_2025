import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
* AUTHOR: Foo, Angel, Jun Xiang
* CREATED: 03/06/2025
* MODIFIED: 07/06/2025
*/
public class MainGui extends JFrame implements ActionListener {
  JPanel sideBarPanel;
  static JPanel contentPanel;
  JButton homePageButton;
  JButton addUserPageButton;
  JButton treatmentPageButton;
  public void mainGui () {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setSize(1200,700);
    this.setTitle("Animal Clinic Management System");

    // Side Bar panel
    sideBarPanel = new JPanel();
    sideBarPanel.setBackground(new Color(255, 244, 223));
    sideBarPanel.setPreferredSize(new Dimension(90,0));
    sideBarPanel.setLayout(new GridLayout(4,1, 5, 50));    
    
    // Home page icon
    ImageIcon homeIcon = new ImageIcon("./icons/home_icon_30px.png");
    homePageButton = new JButton("Home", homeIcon);
    homePageButton.setContentAreaFilled(false); // transparent background
    homePageButton.setBorderPainted(false); // set border to none
    homePageButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    homePageButton.setFocusable(false);
    homePageButton.setVerticalTextPosition(JButton.BOTTOM);
    homePageButton.setHorizontalTextPosition(JButton.CENTER);
    homePageButton.setIconTextGap(5);
    homePageButton.addActionListener(this);
    
    // Add User page icon
    ImageIcon addUserIcon = new ImageIcon("./icons/add_user_icon_30px.png");
    addUserPageButton = new JButton("Create Cutomer", addUserIcon);
    addUserPageButton.setContentAreaFilled(false); // transparent background
    addUserPageButton.setBorderPainted(false); // set border to none
    addUserPageButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    addUserPageButton.setFocusable(false);
    addUserPageButton.setVerticalTextPosition(JButton.BOTTOM);
    addUserPageButton.setHorizontalTextPosition(JButton.CENTER);
    addUserPageButton.setIconTextGap(5);
    addUserPageButton.addActionListener(this);

    // Add page icon
    ImageIcon treatmentIcon = new ImageIcon("./icons/add_icon_30px.png");
    treatmentPageButton = new JButton("Treatment", treatmentIcon);
    treatmentPageButton.setContentAreaFilled(false); // transparent background
    treatmentPageButton.setBorderPainted(false); // set border to none
    treatmentPageButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    treatmentPageButton.setFocusable(false);
    treatmentPageButton.setVerticalTextPosition(JButton.BOTTOM);
    treatmentPageButton.setHorizontalTextPosition(JButton.CENTER);
    treatmentPageButton.setIconTextGap(5);
    treatmentPageButton.addActionListener(this);
    
    // add all button on to side bar
    sideBarPanel.add(homePageButton);
    sideBarPanel.add(addUserPageButton);
    sideBarPanel.add(treatmentPageButton);
    
    this.setLayout(new BorderLayout());
    this.add(sideBarPanel, BorderLayout.WEST);

    // content panel goes here
    contentPanel = new JPanel();
    contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
    contentPanel.setLayout(new CardLayout());
    contentPanel.setBackground(Color.BLACK);

    this.add(contentPanel);

    this.setVisible(true);
    
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == homePageButton) {
      new HomePage().gui(contentPanel);
    } else if (e.getSource() == addUserPageButton) {          
      new AddUserPage().gui(contentPanel);
    } else if (e.getSource() == treatmentPageButton) {
      new HomePage().gui(contentPanel);
    }
  }
}
