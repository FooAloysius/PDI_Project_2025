import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

*/
Author: Angel  
Purpose: All the methods for accessing/mutating payment info  
Created: 15/05/2025  
Modified: 12/06/2025 
/*

public class PaymentPanel extends JPanel {
    private JTextField amountField;
    private JTextField dateField;
    private JTextField balanceField;
    private JTextField statusField;
    private double grandTotal;

    public PaymentPanel(double grandTotal) {
        this.grandTotal = grandTotal;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Payment Method", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        formPanel.add(new JLabel("Payment Date:"));
        dateField = new JTextField(LocalDate.now().toString());
        dateField.setEditable(false);
        formPanel.add(dateField);

        formPanel.add(new JLabel("Grand Amount (RM):"));
        JTextField grandAmountField = new JTextField(String.format("%.2f", grandTotal));
        grandAmountField.setEditable(false);
        formPanel.add(grandAmountField);

        formPanel.add(new JLabel("Amount of Cash / Paid (RM):"));
        amountField = new JTextField();
        formPanel.add(amountField);

        formPanel.add(new JLabel("Balance (RM):"));
        balanceField = new JTextField();
        balanceField.setEditable(false);
        formPanel.add(balanceField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        statusField.setEditable(false);
        formPanel.add(statusField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton cashBtn = new JButton("Pay with Cash");
        JButton cardBtn = new JButton("Pay with Card");
        buttonPanel.add(cashBtn);
        buttonPanel.add(cardBtn);

        // Add panels to layout
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        // Action listeners
        cashBtn.addActionListener(e -> handleCashPayment());
        cardBtn.addActionListener(e -> handleCardPayment());
    }

    private void handleCashPayment() {
        String input = amountField.getText().trim();
        try {
            double paid = Double.parseDouble(input);
            if (paid >= grandTotal) {
                double balance = paid - grandTotal;
                balanceField.setText(String.format("%.2f", balance));
                statusField.setText("Paid");
            } else {
                balanceField.setText("");
                statusField.setText("Insufficient amount of cash!");
            }
        } catch (NumberFormatException ex) {
            statusField.setText("Invalid amount. Please enter a valid number.");
        }
    }

    private void handleCardPayment() {
        String input = amountField.getText().trim();
        try {
            double paid = Double.parseDouble(input);
            if (Math.abs(paid - grandTotal) < 0.01) {
                balanceField.setText("0.00");
                statusField.setText("Paid");
            } else {
                balanceField.setText("");
                statusField.setText("Amount must match the Grand Amount");
            }
        } catch (NumberFormatException ex) {
            statusField.setText("Invalid amount. Please enter a valid number.");
        }
    }
}
