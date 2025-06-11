public  import java.util.List;

public class  Transaction {
    // Fields
    private String transactionID;
    private List<Treatment> treatmentList;
    private double totalAmount;
    private double taxAmount;
    private double consultationFee;
    private double finalAmount;

    // 1. Default Constructor
    public Transaction() {
        this.transactionID = "";
        this.treatmentList = null;
        this.totalAmount = 0.0;
        this.taxAmount = 0.0;
        this.consultationFee = 0.0;
        this.finalAmount = 0.0;
    }

    // 2. Parameterized Constructor
    public Transaction(String id, List<Treatment> list, double consultFee) {
        setTransactionID(id);
        setTreatmentList(list);
        setConsultationFee(consultFee);
        calculateTotalAmount();
        calculateFinalAmount();
    }

    // 3. Copy Constructor
    public Transaction(Transaction copy) {
        this.transactionID = copy.getTransactionID();
        this.treatmentList = copy.getTreatmentList();
        this.totalAmount = copy.calculateTotalAmount();
        this.consultationFee = copy.getConsultationFee();
        this.taxAmount = this.totalAmount * 0.06;
        this.finalAmount = this.totalAmount + this.taxAmount + this.consultationFee;
    }

    // Getter and Setter for transactionID
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String id) {
        if (id != null && !id.isEmpty()) {
            this.transactionID = id;
        }
    }

    // Getter and Setter for treatmentList
    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Treatment> list) {
        this.treatmentList = list;
    }

    // Getter and Setter for consultationFee
    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double fee) {
        if (fee >= 0) {
            this.consultationFee = fee;
        }
    }

    // Calculate totalAmount
    public double calculateTotalAmount() {
        double total = 0.0;
        if (treatmentList != null) {
            for (Treatment t : treatmentList) {
                total += t.getTreatmentFee();
            }
        }
        this.totalAmount = total;
        return totalAmount;
    }

    // Calculate finalAmount
    public double calculateFinalAmount() {
        this.taxAmount = totalAmount * 0.06;
        this.finalAmount = totalAmount + taxAmount + consultationFee;
        return finalAmount;
    }

    // Optional getters if needed
    public double getTotalAmount() {
        return totalAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }
} {
  
}
