import java.util.*;

public class Customer {
    private String customerId;
    private String name;
    private int age;
    private double income;
    private List<Loan> loans;

    public Customer(String customerId, String name, int age, double income) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.income = income;
        this.loans = new ArrayList<>();
    }

    public boolean isEligible(LoanProduct product) {
        return this.income >= product.getMinIncome();
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public double getIncome() { return income; }
}
