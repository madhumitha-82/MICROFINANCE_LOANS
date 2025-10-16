public class LoanProduct {
    private String productId;
    private String name;
    private double interestRate;
    private int tenureMonths;
    private double minIncome;

    public LoanProduct(String productId, String name, double interestRate, int tenureMonths, double minIncome) {
        this.productId = productId;
        this.name = name;
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
        this.minIncome = minIncome;
    }

    public double getInterestRate() { return interestRate; }
    public int getTenureMonths() { return tenureMonths; }
    public double getMinIncome() { return minIncome; }
    public String getProductId() { return productId; }
}
