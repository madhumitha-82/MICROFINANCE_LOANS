
import java.util.*;

public class Loan {
    private String loanId;
    private Customer customer;
    private LoanProduct product;
    private double principal;
    private String status; // active, closed, in_arrears
    private Disbursement disbursement;
    private List<RepaymentSchedule> schedule = new ArrayList<>();
    private List<Repayment> repayments = new ArrayList<>();

    public Loan(String loanId, Customer customer, LoanProduct product, double principal) {
        this.loanId = loanId;
        this.customer = customer;
        this.product = product;
        this.principal = principal;
        this.status = "Pending";
    }

    public void disburse(Date disbursementDate) {
        this.disbursement = new Disbursement(disbursementDate, principal);
        generateSchedule(disbursementDate);
        this.status = "Active";
    }

    private void generateSchedule(Date startDate) {
        double monthlyInterest = (principal * product.getInterestRate()) / 12 / 100;
        double monthlyPrincipal = principal / product.getTenureMonths();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        for (int i = 0; i < product.getTenureMonths(); i++) {
            calendar.add(Calendar.MONTH, 1);
            schedule.add(new RepaymentSchedule(calendar.getTime(), monthlyPrincipal, monthlyInterest));
        }
    }

    public void recordRepayment(double amountPaid, Date date) {
        for (RepaymentSchedule rs : schedule) {
            if (!rs.isPaid()) {
                double totalDue = rs.getPrincipalDue() + rs.getInterestDue();
                Penalty penalty = null;
                if (amountPaid < totalDue) {
                    penalty = new Penalty(50.0, "Partial Payment");
                }
                rs.setPaid(true);
                repayments.add(new Repayment(date, amountPaid, penalty));
                break;
            }
        }
        if (getOutstanding() == 0) {
            this.status = "Closed";
        }
    }

    public double getOutstanding() {
        double totalOutstanding = 0;
        for (RepaymentSchedule rs : schedule) {
            if (!rs.isPaid()) {
                totalOutstanding += rs.getPrincipalDue() + rs.getInterestDue();
            }
        }
        return totalOutstanding;
    }

    public String getStatus() { return status; }
    public String getLoanId() { return loanId; }
    public Customer getCustomer() { return customer; }
}
