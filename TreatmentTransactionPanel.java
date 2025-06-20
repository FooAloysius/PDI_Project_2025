import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
* AUTHOR: Jun Xiang
* CREATED: 25/05/2025
* MODIFIED: 11/06/2025 (Integrated teacher feedback)
*/
public class TreatmentTransactionPanel extends JPanel {
    private Data data;
    private JTable table;
    private JTextField consultationField;
    private JLabel subtotalLabel, taxLabel, totalLabel, ownerLabel, dateTimeLabel;
    private JPanel selectedtreatmentsPanel, selectedTreatmentsList;

    private List<Treatment> treatmentList;

    public TreatmentTransactionPanel(Data data) {
        this.data = data;
        initUI();
    }

    public void initUI() {
        setLayout(new BorderLayout());

        // Pet Selection (ComboBox) 
        String[] petNames = data.getPetsNameList();
        JComboBox<String> petComboBox = new JComboBox<>(petNames);

        //  Pet Owner Display 
        ownerLabel = new JLabel("Pet Owner: -");

        //  DateTime of Visit Field 
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        dateTimeLabel = new JLabel("Date & Time of Visit: " + formattedDateTime);

        JPanel petPanel = new JPanel(new GridLayout(3, 1));
        JPanel topRow = new JPanel(new FlowLayout());
        topRow.add(new JLabel("Select Pet:"));
        topRow.add(petComboBox);
        petPanel.add(topRow);
        petPanel.add(ownerLabel);
        petPanel.add(dateTimeLabel);
        add(petPanel, BorderLayout.NORTH); // Top of panel


        // Pet ComboBox Listener to update Pet Owner 
        petComboBox.addActionListener(e -> {
            int selectedIndex = petComboBox.getSelectedIndex();
            String customerName = this.data.getCustomerName(selectedIndex);
            ownerLabel.setText("Pet Owner: " + customerName);
        });

        // Treatment Table: Multi-Selection Enabled 
        treatmentList = Treatment.loadFromCSV("./csv/Treatments.csv");
        String[] columnNames = {"ID", "Name", "Fee (RM)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Treatment t : treatmentList) {
            tableModel.addRow(new Object[]{t.getTreatmentID(), t.getTreatmentName(), t.getTreatmentFee()});
        }

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Multi-select
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel: Subtotal / Tax / Total 
        JPanel bottomPanel = new JPanel(new GridLayout(5, 3, 5, 5));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Transaction Summary"));

        bottomPanel.add(new JLabel("Consultation Fee (RM):"));
        consultationField = new JTextField("0.00");
        bottomPanel.add(consultationField);

        // added treatments list choosen
        selectedtreatmentsPanel = new JPanel();
        selectedtreatmentsPanel.setPreferredSize(new Dimension(180,400));
        selectedtreatmentsPanel.setBorder(BorderFactory.createTitledBorder("Treatment Selected (Sorted)"));

        selectedTreatmentsList = new JPanel();
        selectedTreatmentsList.setLayout(new BoxLayout(selectedTreatmentsList, BoxLayout.Y_AXIS));
        selectedtreatmentsPanel.add(selectedTreatmentsList);

        
        
        subtotalLabel = new JLabel("Subtotal: RM 0.00");
        bottomPanel.add(subtotalLabel);
        taxLabel = new JLabel("Tax (10%): RM 0.00");
        bottomPanel.add(taxLabel);
        totalLabel = new JLabel("Grand Total: RM 0.00");
        bottomPanel.add(totalLabel);
        
        JButton calcButton = new JButton("Calculate Total");
        bottomPanel.add(calcButton);

        add(bottomPanel, BorderLayout.SOUTH);
        add(selectedtreatmentsPanel, BorderLayout.EAST);

        //  Button Action: Calculate Total from multiple treatments 
        calcButton.addActionListener(e -> calculateTotal());
    }

    private void calculateTotal() {
        try {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(this, "Please select at least one treatment.");
                return;
            }

            double consultationFee = Double.parseDouble(consultationField.getText());

            //Consultation Fee Validation 
            if (consultationFee < 0) {
                JOptionPane.showMessageDialog(this, "Consultation fee cannot be negative.");
                return;
            } else if (consultationFee < 1 || consultationFee > 1000) {
                JOptionPane.showMessageDialog(this, "Consultation fee must be between RM 1 and RM 1000.");
                return;
            }

            // added treatments list selected
            selectedTreatmentsList.removeAll();

            List<String[]> selectedTreatments = new ArrayList<>();
            for (int row: selectedRows) {
                selectedTreatments.add(new String[] {treatmentList.get(row).getTreatmentID(), treatmentList.get(row).getTreatmentName(), String.valueOf(treatmentList.get(row).getTreatmentFee())});
            }
            selectedTreatments.sort(Comparator.comparing(treatment -> treatment[1])); // sorting selected treatments
            
            for (String[] treatment : selectedTreatments) {
                selectedTreatmentsList.add(new JLabel("(" + treatment[0] + ") " + treatment[1] + ": " + treatment[2]));
            }

            //Subtotal Calculation 
            double subtotal = 0.0;
            for (int row : selectedRows) {
                subtotal += treatmentList.get(row).getTreatmentFee();
            }

            // added subtotal under treatment list selected
            selectedTreatmentsList.add(new JLabel("Subtotal: " + subtotal));

            double tax = subtotal * 0.10;
            double grandTotal = subtotal + tax + consultationFee;
    
            // UPDATE shared variable for use in PaymentPanel
            Main.latestTotalFee = grandTotal;

            //Update Summary Labels 
            subtotalLabel.setText(String.format("Subtotal: RM %.2f", subtotal));
            taxLabel.setText(String.format("Tax (10%%): RM %.2f", tax));
            totalLabel.setText(String.format("Grand Total: RM %.2f", grandTotal));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid consultation fee.");
        }
    }

    public void gui(JPanel panel) {
        panel.removeAll();
        panel.add(this);
        panel.revalidate();
        panel.repaint();
    }

    // utility for receipt
    public List<String[]> getSelectedTreatmentNames() {
        int[] selectedRows = table.getSelectedRows();
        List<String[]> selectedRecords = new ArrayList<>();

        for (int row : selectedRows) {
            String treatmentID = treatmentList.get(row).getTreatmentID();
            String treatmentName = treatmentList.get(row).getTreatmentName();
            selectedRecords.add(new String[]{treatmentID, treatmentName});
        }

        selectedRecords.sort(Comparator.comparing(record -> record[1])); // sorting

        return selectedRecords;
    }
}


