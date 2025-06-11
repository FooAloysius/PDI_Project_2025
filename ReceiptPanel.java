import java.awt.*;
import javax.swing.*;

public class ReceiptPanel extends JPanel {
    private JTextArea receiptArea;

    public ReceiptPanel(Transaction transaction, Payment payment) {
        setLayout(new BorderLayout());

        Receipt receipt = new Receipt(transaction, payment);

        receiptArea = new JTextArea(20, 40);
        receiptArea.setEditable(false);
        receiptArea.setText(receipt.generateReceiptText());

        add(new JScrollPane(receiptArea), BorderLayout.CENTER);
    }
}
