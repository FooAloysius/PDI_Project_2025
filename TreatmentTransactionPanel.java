import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
* AUTHOR: Jun Xiang
* CREATED: 25/05/2025
* MODIFIED: 05/06/2025
*/
public class TreatmentTransactionPanel extends JPanel {
    private JTable table;
    private JTextField consultationField;
    private JLabel subtotalLabel, taxLabel, totalLabel;

    private List<Treatment> treatmentList;
    private List<Treatment> selectedTreatments = new ArrayList<>();

    public TreatmentTransactionPanel() {
        setLayout(new BorderLayout());

        // Load treatments from CSV
        treatmentList = Treatment.loadFromCSV("treatments.csv");

        // Table data
        String[] columnNames = {"ID", "Name", "Fee (RM)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Treatment t : treatmentList) {
            tableModel.addRow(new Object[]{t.getTreatmentID(), t.getTreatmentName(), t.getTreatmentFee()});
        }

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Consultation fee + output panel
        JPanel bottomPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Transaction Details"));

        bottomPanel.add(new JLabel("Consultation Fee (RM):"));
        consultationField = new JTextField("0.00");
        bottomPanel.add(consultationField);

        subtotalLabel = new JLabel("Subtotal: RM 0.00");
        taxLabel = new JLabel("Tax (6%): RM 0.00");
        totalLabel = new JLabel("Total: RM 0.00");

        bottomPanel.add(subtotalLabel);
        bottomPanel.add(taxLabel);
        bottomPanel.add(new JLabel()); 
        bottomPanel.add(totalLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Update total when table selection changes or consultation fee changes
        table.getSelectionModel().addListSelectionListener(e -> updateTotal());
        consultationField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateTotal();
            }
        });
    }

    private void updateTotal() {
        try {
            selectedTreatments.clear();
            int[] selectedRows = table.getSelectedRows();
            for (int row : selectedRows) {
                selectedTreatments.add(treatmentList.get(row));
            }

            double consultationFee = Double.parseDouble(consultationField.getText());

            Transaction tx = new Transaction("TX001", selectedTreatments, consultationFee);
            tx.calculateTotalAmount();
            tx.calculateFinalAmount();

            subtotalLabel.setText(String.format("Subtotal: RM %.2f", tx.getTotalAmount()));
            taxLabel.setText(String.format("Tax (6%%): RM %.2f", tx.getTaxAmount()));
            totalLabel.setText(String.format("Total: RM %.2f", tx.getFinalAmount()));

        } catch (NumberFormatException ex) {
            subtotalLabel.setText("Subtotal: RM 0.00");
            taxLabel.setText("Tax (6%): RM 0.00");
            totalLabel.setText("Total: RM 0.00");
        }
    }

    // Main method for standalone testing
    public static void main(String[] args) {
        JFrame frame = new JFrame("Treatment Transaction Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new TreatmentTransactionPanel());
        frame.setVisible(true);
    }
}

