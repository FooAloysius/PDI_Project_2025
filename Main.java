/* REFERENCE
 * https://coderanch.com/t/331731/java/Resize-ImageIcon
 * https://dev.to/pavel_polivka/double-comparison-in-java-1b7
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*; 

/*
* AUTHOR: Foo, Angel, Jun Xiang
* CREATED: 03/06/2025
* MODIFIED: 12/06/2025
*/
public class Main extends JFrame implements ActionListener {
  JPanel sideBarPanel;
  static JPanel contentPanel;
  JButton homePageButton;
  JButton userPageButton;
  JButton treatmentPageButton;
  JButton paymentPageButton;
  static Data data;
  public static double latestTotalFee = 0.00 ;  
  TreatmentTransactionPanel treatmentTransactionPanel;
  public static List<String> selectedTreatmentNames;  // Declare selectedTreatmentNames

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
    userPageButton = new JButton("Cutomer", userIcon);
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

    // Payment page icon
    ImageIcon paymentIcon = new ImageIcon(((new ImageIcon("./icons/payment_icon_1024px.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
    paymentPageButton = new JButton("Payment", paymentIcon);
    paymentPageButton.setContentAreaFilled(false);
    paymentPageButton.setBorderPainted(false);
    paymentPageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    paymentPageButton.setFocusable(false);
    paymentPageButton.setVerticalTextPosition(JButton.BOTTOM);
    paymentPageButton.setHorizontalTextPosition(JButton.CENTER);
    paymentPageButton.setIconTextGap(5);
    paymentPageButton.addActionListener(this);
  
    // add all button on to side bar
    sideBarPanel.add(homePageButton);
    sideBarPanel.add(userPageButton);
    sideBarPanel.add(treatmentPageButton);
    sideBarPanel.add(paymentPageButton);
    
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
      treatmentTransactionPanel = new TreatmentTransactionPanel(data);
      treatmentTransactionPanel.gui(contentPanel);
    } else if (e.getSource() == paymentPageButton) {
      showPaymentDialog();
    }
  }

  private void showPaymentDialog() {
    String[] options = {"Cash", "Card"};
    String selected = (String) JOptionPane.showInputDialog(
      this,
      "Choose your payment method:",
      "Payment Method",
      JOptionPane.PLAIN_MESSAGE,
      null,
      options,
      options[0]
    );

    if (selected != null) {
      JFrame paymentFrame = new JFrame("Payment Panel");
      paymentFrame.setSize(400, 300);

      JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      JLabel dateLabel = new JLabel("Payment Date:");
      JTextField dateField = new JTextField(LocalDate.now().format(formatter));
      dateField.setEditable(false);

      JLabel amountLabel = new JLabel("Grand Amount (RM):");
      JTextField amountField = new JTextField(String.format("%.2f", latestTotalFee));
      amountField.setEditable(false);

      JLabel paidLabel = new JLabel(selected.equals("Cash") ? "Amount of Cash (RM):" : "Amount Paid (RM):");
      JTextField paidField = new JTextField();

      JLabel balanceLabel = new JLabel("Balance (RM):");
      JTextField balanceField = new JTextField();
      balanceField.setEditable(false);

      JLabel statusLabel = new JLabel("Status:");
      JTextField statusField = new JTextField();
      statusField.setEditable(false);

      JButton checkButton = new JButton("Check Payment");
      checkButton.addActionListener(ae -> {
        try {
          double paid = Double.parseDouble(paidField.getText());
          if (selected.equals("Cash")) {
            if (paid >= latestTotalFee) {
              balanceField.setText(String.format("%.2f", paid - latestTotalFee));
              statusField.setText("Paid");
            
            // Add ReceiptPanel on successful CASH payment
              ReceiptPanel receiptPanel = new ReceiptPanel(
                  treatmentTransactionPanel.getSelectedTreatmentNames(),
                  latestTotalFee,
                  "Cash",
                  paid
              );
              contentPanel.removeAll();
              contentPanel.add(receiptPanel);
              contentPanel.revalidate();
              contentPanel.repaint();
              paymentFrame.dispose();

            } else {
              balanceField.setText("0.00");
              statusField.setText("Insufficient amount of cash!");
            }
          } else {
            // bank payment
            final double EPSILON = 0.01; // make absolute value, and compare if the result is smaller than 0.01
            if (Math.abs(paid - latestTotalFee) < EPSILON) {
              balanceField.setText("0.00");
              statusField.setText("Paid");

            // Add ReceiptPanel on successful CARD payment
            ReceiptPanel receiptPanel = new ReceiptPanel(
                  treatmentTransactionPanel.getSelectedTreatmentNames(),
                  latestTotalFee,
                  "Card",
                  paid
              );
              contentPanel.removeAll();
              contentPanel.add(receiptPanel);
              contentPanel.revalidate();
              contentPanel.repaint();
              paymentFrame.dispose();

            } else {
              balanceField.setText("0.00");
              statusField.setText("Amount paid must match the Grand Amount!");
            }
          }
        } catch (NumberFormatException ex) {
          statusField.setText("Invalid amount. Please enter a valid number.");
        }
      });

      panel.add(dateLabel);
      panel.add(dateField);
      panel.add(amountLabel);
      panel.add(amountField);
      panel.add(paidLabel);
      panel.add(paidField);
      panel.add(balanceLabel);
      panel.add(balanceField);
      panel.add(statusLabel);
      panel.add(statusField);

      paymentFrame.add(panel, BorderLayout.CENTER);
      paymentFrame.add(checkButton, BorderLayout.SOUTH);

      paymentFrame.setVisible(true);
    }
  }
}


