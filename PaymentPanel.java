import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentPanel extends JPanel {
    private JTextField methodField;
    private JTextField amountField;
    private JTextField dateField;
    private JTextArea summaryArea;
    private JButton processButton;

    public PaymentPanel() {
        setLayout(new BorderLayout());

        // --- Top input panel ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        inputPanel.add(new JLabel("Payment Method (Cash/Card):"));
        methodField = new JTextField();
        inputPanel.add(methodField);

        inputPanel.add(new JLabel("Amount Paid (RM):"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Payment Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        processButton = new JButton("Process Payment");
        inputPanel.add(processButton);

        add(inputPanel, BorderLayout.NORTH);

        // --- Summary area ---
        summaryArea = new JTextArea(10, 30);
        summaryArea.setEditable(false);
        add(new JScrollPane(summaryArea), BorderLayout.CENTER);

        // --- Button action ---
        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String method = methodField.getText().trim();
                String amountText = amountField.getText().trim();
                String date = dateField.getText().trim();

                try {
                    double amount = Double.parseDouble(amountText);
                    Payment payment = new Payment(method, amount, date);
                    payment.processPayment(50.00); // simulate RM50 totalCost for now
                    summaryArea.setText(payment.getPaymentSummary());
                } catch (NumberFormatException ex) {
                    summaryArea.setText("Invalid amount. Please enter a valid number.");
                }
            }
        });
    }
}
