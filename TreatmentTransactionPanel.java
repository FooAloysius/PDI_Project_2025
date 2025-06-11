import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/*
* AUTHOR: Jun Xiang
* CREATED: 25/05/2025
* MODIFIED: 10/06/2025 (Added Pet ComboBox)
*/
public class TreatmentTransactionPanel extends JPanel {
    private JTable table;
    private JTextField consultationField;
    private JLabel totalLabel;


    private List<Treatment> treatmentList;


    public TreatmentTransactionPanel() {
        setLayout(new BorderLayout());


       
        Data data = new Data();
        data.importPets();
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


        // === Step 3: 咨询费 + 总价 ===
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


        // === Step 4: 绑定按钮行为 ===
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


   
    public static void gui(JPanel panel) {
        panel.removeAll();
        panel.add(new TreatmentTransactionPanel());
        panel.revalidate();
        panel.repaint();
    }
}



