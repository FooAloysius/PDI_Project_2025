import java.text.DecimalFormat;
import java.util.Comparator;

public class Receipt {
  // Fields
  private Transaction transaction;
  private Payment payment;

  // Default Constructor
  public Receipt() {
    this.transaction = null;
    this.payment = null;
  }

  // Parameterized Constructor
  public Receipt(Transaction transaction, Payment payment) {
    this.transaction = transaction;
    this.payment = payment;
  }

  // Copy Constructor
  public Receipt(Receipt other) {
    this.transaction = other.transaction;
    this.payment = other.payment;
  }

  // Method: Generate Receipt Text
  public String generateReceiptText() {
    StringBuilder sb = new StringBuilder();
    DecimalFormat df = new DecimalFormat("0.00");

    sb.append("=== Animal Clinic Receipt ===\n");
    sb.append("Date of Visit: ").append(transaction.getTransactionID()).append("\n");

    sb.append("--- Treatments ---\n");
    transaction.getTreatmentList().sort(Comparator.comparing(Treatment::getTreatmentName));

    for (Treatment t : transaction.getTreatmentList()) {
      sb.append(t.getTreatmentName())
        .append(" - RM ")
        .append(df.format(t.getTreatmentFee()))
        .append("\n");
    }

    sb.append("Consultation Fee: RM ").append(df.format(transaction.getConsultationFee())).append("\n");
    sb.append("Subtotal (Before Tax): RM ").append(df.format(transaction.getTotalAmount())).append("\n");
    sb.append("Tax (6%): RM ").append(df.format(transaction.getTaxAmount())).append("\n");
    sb.append("Total: RM ").append(df.format(transaction.getFinalAmount())).append("\n\n");

    sb.append("--- Payment Details ---\n");
    sb.append(payment.getPaymentSummary());

    return sb.toString();
  }

  // Getters and Setters
  public Transaction getTransaction() { return transaction; }
  public Payment getPayment() { return payment; }
  public void setTransaction(Transaction transaction) { this.transaction = transaction; }
  public void setPayment(Payment payment) { this.payment = payment; }
}
