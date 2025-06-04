import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/*
* AUTHOR: Foo
* CREATED: 03/06/2025
* MODIFIED: 04/06/2025
*/
public class MainGui extends JFrame {
  public void mainGui () {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setSize(1200,700);
    this.setTitle("Animal Clinic Management System");

    // Side Bar panel
    JPanel sideBarPanel = new JPanel();
    sideBarPanel.setBackground(new Color(255, 244, 223));
    sideBarPanel.setPreferredSize(new Dimension(90,0));
    sideBarPanel.setLayout(new GridLayout(4,1, 5, 50));    
    
    // Home page icon
    ImageIcon homeIcon = new ImageIcon("./icons/home_icon_30px.png");
    JButton homePageButton = new JButton("Home", homeIcon);
    homePageButton.setContentAreaFilled(false); // transparent background
    homePageButton.setBorderPainted(false); // set border to none
    homePageButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    homePageButton.setFocusable(false);
    homePageButton.setVerticalTextPosition(JButton.BOTTOM);
    homePageButton.setHorizontalTextPosition(JButton.CENTER);
    homePageButton.setIconTextGap(5);
    
    // Add User page icon
    ImageIcon addUserIcon = new ImageIcon("./icons/add_user_icon_30px.png");
    JButton addUserPageButton = new JButton("Create Cutomer", addUserIcon);
    addUserPageButton.setContentAreaFilled(false); // transparent background
    addUserPageButton.setBorderPainted(false); // set border to none
    addUserPageButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // when mouse hover, set cursor to pointer
    addUserPageButton.setFocusable(false);
    addUserPageButton.setVerticalTextPosition(JButton.BOTTOM);
    addUserPageButton.setHorizontalTextPosition(JButton.CENTER);
    addUserPageButton.setIconTextGap(5);
    
    // add all button on to side bar
    sideBarPanel.add(homePageButton);
    sideBarPanel.add(addUserPageButton);
    
    this.setLayout(new BorderLayout());
    this.add(sideBarPanel, BorderLayout.WEST);

    this.setVisible(true);
  }
}
