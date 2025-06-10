import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
* AUTHOR: Jun Xiang
* CREATED: 25/05/2025
* MODIFIED: 06/06/2025 (Simplified)
*/
public class TreatmentTransactionPanel extends JPanel {
    private JTable table;
    private JTextField consultationField;
    private JLabel totalLabel;

    private List<Treatment> treatmentList;

    public TreatmentTransactionPanel() {
        setLayout(new BorderLayout());

        // Load treatments from CSV
        treatmentList = Treatment.loadFromCSV("./csv/Treatments.csv");

        // Table setup
        String[] columnNames = {"ID", "Name", "Fee (RM)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Treatment t : treatmentList) {
            tableModel.addRow(new Object[]{t.getTreatmentID(), t.getTreatmentName(), t.getTreatmentFee()});
        }

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Consultation & Total"));

        bottomPanel.add(new JLabel("Consultation Fee (RM):"));
        consultationField = new JTextField("0.00");
        bottomPanel.add(consultationField);

        JButton calcButton = new JButton("Calculate Total");
        bottomPanel.add(calcButton);

        totalLabel = new JLabel("Final Total: RM 0.00");
        bottomPanel.add(totalLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button listener
        calcButton.addActionListener(e -> calculateTotal());
    }

    private void calculateTotal() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select one treatment.");
                return;
            }

            double treatmentFee = treatmentList.get(selectedRow).getTreatmentFee();
            double consultationFee = Double.parseDouble(consultationField.getText());

            double finalTotal = treatmentFee + (treatmentFee * 0.06) + consultationFee;

            totalLabel.setText(String.format("Final Total: RM %.2f", finalTotal));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid consultation fee.");
        }
    }

    // Optional main method for testing
    public void gui(JPanel panel) {
        JFrame frame = new JFrame("Simplified Treatment Transaction Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new TreatmentTransactionPanel());
        frame.setVisible(true);
        
        String[] pets = new Data().getPetsNameList();

        for (int i = 0; i < pets.length; i++) {
            System.out.println(pets[i]);
        }
    }
}
