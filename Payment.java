import java.text.DecimalFormat;

public class Payment {
  // Fields
  private String paymentMethod;
  private double amountPaid;
  private double balanceReturned;
  private String paymentDate;
  private String status;

  // Default Constructor
  public Payment() {
    this.paymentMethod = "";
    this.amountPaid = 0.0;
    this.paymentDate = "";
    this.status = "Pending";
    this.balanceReturned = 0.0;
  }

  // Parameterized Constructor
  public Payment(String paymentMethod, double amountPaid, String paymentDate) {
    this.paymentMethod = paymentMethod;
    this.amountPaid = amountPaid;
    this.paymentDate = paymentDate;
    this.status = "Pending";
    this.balanceReturned = 0.0;
  }

  // Copy Constructor
  public Payment(Payment other) {
    this.paymentMethod = other.paymentMethod;
    this.amountPaid = other.amountPaid;
    this.paymentDate = other.paymentDate;
    this.status = other.status;
    this.balanceReturned = other.balanceReturned;
  }

  // Method: Process Payment
  public void processPayment(double totalCost) {
    DecimalFormat df = new DecimalFormat("0.00");

    if (paymentMethod.equalsIgnoreCase("Cash")) {
      if (amountPaid >= totalCost) {
        balanceReturned = amountPaid - totalCost;
        balanceReturned = Double.parseDouble(df.format(balanceReturned));
        status = "Paid";
      } else {
        System.out.println("Error: Insufficient cash payment.");
        status = "Failed";
      }
    } else if (paymentMethod.equalsIgnoreCase("Card")) {
      balanceReturned = 0.0;
      status = "Paid";
    } else {
      System.out.println("Error: Unknown payment method.");
      status = "Failed";
    }
  }

  // Method: Get Payment Summary
  public String getPaymentSummary() {
    DecimalFormat df = new DecimalFormat("0.00");
    return "Payment Method: " + paymentMethod +
           "\nAmount Paid: RM " + df.format(amountPaid) +
           "\nBalance Returned: RM " + df.format(balanceReturned) +
           "\nPayment Date: " + paymentDate +
           "\nStatus: " + status;
  }

  // Getters
  public String getPaymentMethod() { return paymentMethod; }
  public double getAmountPaid() { return amountPaid; }
  public double getBalanceReturned() { return balanceReturned; }
  public String getPaymentDate() { return paymentDate; }
  public String getStatus() { return status; }

  // Setters
  public void setPaymentMethod(String value) { this.paymentMethod = value; }
  public void setAmountPaid(double value) { this.amountPaid = value; }
  public void setBalanceReturned(double value) { this.balanceReturned = value; }
  public void setPaymentDate(String value) { this.paymentDate = value; }
  public void setStatus(String value) { this.status = value; }
}
