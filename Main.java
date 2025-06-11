/* REFERENCE
 * https://coderanch.com/t/331731/java/Resize-ImageIcon
 * 
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
* AUTHOR: Foo, Angel, Jun Xiang
* CREATED: 03/06/2025
* MODIFIED: 11/06/2025
*/
public class Main extends JFrame implements ActionListener {
  JPanel sideBarPanel;
  static JPanel contentPanel;
  JButton homePageButton;
  JButton userPageButton;
  JButton treatmentPageButton;
  static Data data;

  // GUI start from here
  public void mainGui () {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setSize(1200,700);
    this.setTitle("Animal Clinic Management System");

    // Side Bar panel
    sideBarPanel = new JPanel();
    sideBarPanel.setBackground(new Color(255, 254, 236));
    sideBarPanel.setPreferredSize(new Dimension(150,0));
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
    ImageIcon treatmentIcon = new ImageIcon(((new ImageIcon("./icons/treatment_icon_200px.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
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
    
    this.setLayout(new BorderLayout()); // set JFrame to border layout
    this.add(sideBarPanel, BorderLayout.WEST); // added sidebar to JFrame

    // content panel goes here
    contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
    contentPanel.setBackground(Color.WHITE);
    new HomePage().gui(contentPanel, data); // set default page as HomePage

    this.add(contentPanel);

    this.setVisible(true);
    
  }
  
  /* main method for the whole program */
  public static void main(String[] args) {
    data = new Data();
    data.init(); // initialize all data
    new Main().mainGui(); // starting GUI
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {

    // left side bar panel button ActionEvent listener here
    if (e.getSource() == homePageButton) {
      new HomePage().gui(contentPanel, data);
    } else if (e.getSource() == userPageButton) {          
      new CustomerPage().gui(contentPanel, data);
    } else if (e.getSource() == treatmentPageButton) {
      new TreatmentTransactionPanel(data).gui(contentPanel);
    }
  }
}
