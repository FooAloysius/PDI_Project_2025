import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
* AUTHOR: Jun Xiang
* CREATED: 25/05/2025
* MODIFIED: 10/06/2025 (Added Pet ComboBox + Consultation Fee Validation)
*/
public class TreatmentTransactionPanel extends JPanel {
    private JTable table;
    private JTextField consultationField;
    private JLabel totalLabel;

    private List<Treatment> treatmentList;

    public TreatmentTransactionPanel() {
        setLayout(new BorderLayout());

        Data data = new Data();
        String[] petNames = data.getPetsNameList();

        JComboBox<String> petComboBox = new JComboBox<>(petNames);
        JPanel petPanel = new JPanel(new FlowLayout());
        petPanel.add(new JLabel("Select Pet:"));
        petPanel.add(petComboBox);
        add(petPanel, BorderLayout.NORTH); //

        treatmentList = Treatment.loadFromCSV("./csv/Treatments.csv");
        String[] columnNames = {"ID", "Name", "Fee (RM)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Treatment t : treatmentList) {
            tableModel.addRow(new Object[]{t.getTreatmentID(), t.getTreatmentName(), t.getTreatmentFee()});
        }

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // === Step 3: Consultation Fee + Total ===
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

        // === Step 4: Button Action ===
        calcButton.addActionListener(e -> calculateTotal());
    }

    private void calculateTotal() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select one treatment.");
                return;
            }

            double consultationFee = Double.parseDouble(consultationField.getText());

            // Validate consultation fee range
            if (consultationFee < 0) {
                JOptionPane.showMessageDialog(this, "Consultation fee cannot be negative.");
                return;
            } else if (consultationFee < 1 || consultationFee > 1000) {
                JOptionPane.showMessageDialog(this, "Consultation fee must be between RM 1 and RM 1000.");
                return;
            }

            double treatmentFee = treatmentList.get(selectedRow).getTreatmentFee();
            double finalTotal = treatmentFee + (treatmentFee * 0.06) + consultationFee;

            totalLabel.setText(String.format("Final Total: RM %.2f", finalTotal));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid consultation fee.");
        }
    }

    public static void gui(JPanel panel) {
        panel.removeAll();
        panel.add(new TreatmentTransactionPanel());
        panel.revalidate();
        panel.repaint();
    }
}


