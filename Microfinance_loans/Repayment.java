import java.util.Date;

public class Repayment {
    private Date repaymentDate;
    private double amountPaid;
    private Penalty penalty;

    public Repayment(Date repaymentDate, double amountPaid, Penalty penalty) {
        this.repaymentDate = repaymentDate;
        this.amountPaid = amountPaid;
        this.penalty = penalty;
    }
}
