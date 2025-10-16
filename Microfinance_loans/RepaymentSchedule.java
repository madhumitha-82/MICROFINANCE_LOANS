import java.util.Date;

public class RepaymentSchedule {
    private Date dueDate;
    private double principalDue;
    private double interestDue;
    private boolean isPaid;

    public RepaymentSchedule(Date dueDate, double principalDue, double interestDue) {
        this.dueDate = dueDate;
        this.principalDue = principalDue;
        this.interestDue = interestDue;
        this.isPaid = false;
    }

    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { this.isPaid = paid; }
    public double getPrincipalDue() { return principalDue; }
    public double getInterestDue() { return interestDue; }
}
