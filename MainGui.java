import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// reference for ImageIcon resize https://coderanch.com/t/331731/java/Resize-ImageIcon
/*
* AUTHOR: Foo, Angel, Jun Xiang
* CREATED: 03/06/2025
* MODIFIED: 07/06/2025
*/
public class MainGui extends JFrame implements ActionListener {
  JPanel sideBarPanel;
  static JPanel contentPanel;
  JButton homePageButton;
  JButton userPageButton;
  JButton treatmentPageButton;
  public void mainGui () {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setSize(1200,700);
    this.setTitle("Animal Clinic Management System");

    // Side Bar panel
    sideBarPanel = new JPanel();
    sideBarPanel.setBackground(new Color(255, 254, 236));
    sideBarPanel.setPreferredSize(new Dimension(90,0));
    sideBarPanel.setLayout(new GridLayout(4,1, 5, 50));
    
    // Home page icon
    ImageIcon homeIcon = new ImageIcon(((new ImageIcon("./icons/home_icon_200px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
    homePageButton = new JButton("Home", homeIcon);
    homePageButton.setContentAreaFilled(false); // transparent background
    homePageButton.setBorderPainted(false); // set border to none
    homePageButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    homePageButton.setFocusable(false);
    homePageButton.setVerticalTextPosition(JButton.BOTTOM);
    homePageButton.setHorizontalTextPosition(JButton.CENTER);
    homePageButton.setIconTextGap(5);
    homePageButton.addActionListener(this);
    
    // User page icon
    ImageIcon userIcon = new ImageIcon(((new ImageIcon("./icons/user_icon_229px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
    userPageButton = new JButton("Create Cutomer", userIcon);
    userPageButton.setContentAreaFilled(false); // transparent background
    userPageButton.setBorderPainted(false); // set border to none
    userPageButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    userPageButton.setFocusable(false);
    userPageButton.setVerticalTextPosition(JButton.BOTTOM);
    userPageButton.setHorizontalTextPosition(JButton.CENTER);
    userPageButton.setIconTextGap(5);
    userPageButton.addActionListener(this);
    userPageButton.setBounds(0, 0, 30, 30);

    // Add page icon
    ImageIcon treatmentIcon = new ImageIcon(((new ImageIcon("./icons/add_icon_229px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
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
    sideBarPanel.add(userPageButton);
    sideBarPanel.add(treatmentPageButton);
    
    this.setLayout(new BorderLayout());
    this.add(sideBarPanel, BorderLayout.WEST);

    // content panel goes here
    contentPanel = new JPanel();
    contentPanel.setLayout(new CardLayout());
    contentPanel.setBackground(Color.WHITE);
    new HomePage().gui(contentPanel); // set default page as HomePage

    this.add(contentPanel);

    this.setVisible(true);
    
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == homePageButton) {
      new HomePage().gui(contentPanel);
    } else if (e.getSource() == userPageButton) {          
      new AddUserPage().gui(contentPanel);
    } else if (e.getSource() == treatmentPageButton) {
      new HomePage().gui(contentPanel);
    }
  }
}
