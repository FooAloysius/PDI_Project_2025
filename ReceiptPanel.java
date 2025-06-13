import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class ReceiptPanel extends JPanel {

/*
Author: Angel  
Purpose: All the methods for accessing/mutating receipt info  
Created: 15/05/2025  
Modified: 12/06/2025 
*/  
    
    public ReceiptPanel(List<String[]> treatments, double grandAmount, String paymentMethod, double paidAmount) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        String paymentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        // Title
        JLabel titleLabel = new JLabel("Animal Clinic Receipt", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Receipt Details
        JTextArea receiptArea = new JTextArea(12, 40);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        receiptArea.setEditable(false);

        StringBuilder receiptText = new StringBuilder();
        receiptText.append("Date            : ").append(paymentDate).append("\n");
        receiptText.append("Payment Method  : ").append(paymentMethod).append("\n");
        receiptText.append("--------------------------------------------\n");
        receiptText.append("Selected Treatments (Sorted):\n");

        for (String[] treatment : treatments) {
            receiptText.append(" - (").append(treatment[0]).append(") ").append(treatment[1]).append("\n");
        }

        receiptText.append("--------------------------------------------\n");
        receiptText.append(String.format("Grand Amount    : RM %.2f\n", grandAmount));
        receiptText.append(String.format("Amount Paid     : RM %.2f\n", paidAmount));

        if (paymentMethod.equalsIgnoreCase("Cash")) {
            double balance = paidAmount - grandAmount;
            receiptText.append(String.format("Balance         : RM %.2f\n", balance));
        }

        receiptText.append("Status          : PAID\n");
        receiptText.append("--------------------------------------------\n");
        receiptText.append("   Thank you for visiting our clinic! \n");

        receiptArea.setText(receiptText.toString());
        this.add(new JScrollPane(receiptArea));

        // Save to file
        saveReceiptToFile(receiptText.toString());
    }

    private void saveReceiptToFile(String receiptContent) {
    try {
        // Created a folder name 'Receipts_file' to store the generated receipts
        File folder = new File("Receipts_file");
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Filename is included with date and time
        String fileName = "receipt_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
        File receiptFile = new File(folder, fileName);

        // Write the receipt content to file
        try (FileWriter writer = new FileWriter(receiptFile)) {
            writer.write(receiptContent);
            JOptionPane.showMessageDialog(this, "Receipt saved in: Receipts_file/" + fileName,
                    "Receipt Generated", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Failed to save receipt.",
                "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

}
