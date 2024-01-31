public class SavingsAccount extends BankAccount{
    private double interestRate;

    public SavingsAccount(double initialAmount, double rate) {
        super(initialAmount);
        this.interestRate = rate;
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    public void calculateInterest() {
        double interest = balance * interestRate;
        deposit(interest);
    }
    
    public String toString() {
        return String.format("SavingsAccount: balance $%.2f, interest rate %.2f", balance, interestRate);
    }
    
    public static void main(String[] args) {
        SavingsAccount myAccount = new SavingsAccount(100.0,0.15);
	myAccount.calculateInterest();
	System.out.println(myAccount.toString());
    }
}

