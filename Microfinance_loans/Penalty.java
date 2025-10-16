public class Penalty {
    private double amount;
    private String reason;

    public Penalty(double amount, String reason) {
        this.amount = amount;
        this.reason = reason;
    }

    public double getAmount() { return amount; }
    public String getReason() { return reason; }
}
