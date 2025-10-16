import java.util.*;

public class MicrofinanceApp {
    static Scanner sc = new Scanner(System.in);
    static List<Customer> customers = new ArrayList<>();
    static List<LoanProduct> products = new ArrayList<>();
    static List<Loan> loans = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Microfinance Loan System ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Loan Product");
            System.out.println("3. Approve Loan");
            System.out.println("4. Disburse Loan");
            System.out.println("5. View Outstanding");
            System.out.println("6. Record Repayment");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> addLoanProduct();
                case 3 -> approveLoan();
                case 4 -> disburseLoan();
                case 5 -> viewOutstanding();
                case 6 -> recordRepayment();
                case 7 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        System.out.print("Enter income: ");
        double income = sc.nextDouble();
        customers.add(new Customer(id, name, age, income));
        System.out.println("Customer added.");
    }

    private static void addLoanProduct() {
        System.out.print("Enter Product ID: ");
        String pid = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Interest Rate (%): ");
        double rate = sc.nextDouble();
        System.out.print("Tenure (months): ");
        int tenure = sc.nextInt();
        System.out.print("Min Income: ");
        double minIncome = sc.nextDouble();
        products.add(new LoanProduct(pid, name, rate, tenure, minIncome));
        System.out.println("Loan product added.");
    }

    private static void approveLoan() {
        System.out.print("Customer ID: ");
        String cid = sc.nextLine();
        Customer customer = customers.stream()
                .filter(c -> c.getCustomerId().equals(cid))
                .findFirst().orElse(null);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Loan Product ID: ");
        String pid = sc.nextLine();
        LoanProduct product = products.stream()
                .filter(p -> p.getProductId().equals(pid))
                .findFirst().orElse(null);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (!customer.isEligible(product)) {
            System.out.println("Customer is not eligible.");
            return;
        }

        System.out.print("Enter loan amount: ");
        double amount = sc.nextDouble();
        Loan loan = new Loan("L" + (loans.size() + 1), customer, product, amount);
        loans.add(loan);
        customer.addLoan(loan);
        System.out.println("Loan approved.");
    }

    private static void disburseLoan() {
        Loan loan = selectLoan();
        if (loan != null) {
            loan.disburse(new Date());
            System.out.println("Loan disbursed and schedule activated.");
        }
    }

    private static void viewOutstanding() {
        Loan loan = selectLoan();
        if (loan != null) {
            System.out.println("Outstanding balance: " + loan.getOutstanding());
            System.out.println("Loan status: " + loan.getStatus());
        }
    }

    private static void recordRepayment() {
        Loan loan = selectLoan();
        if (loan != null) {
            System.out.print("Amount Paid: ");
            double amt = sc.nextDouble();
            loan.recordRepayment(amt, new Date());
            System.out.println("Repayment recorded.");
        }
    }

    private static Loan selectLoan() {
        System.out.print("Enter Loan ID: ");
        String lid = sc.nextLine();
        return loans.stream()
                .filter(l -> l.getLoanId().equals(lid))
                .findFirst().orElse(null);
    }
}
