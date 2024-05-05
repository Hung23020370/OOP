public class FeeAccount extends BankAccount{
    private static final double fee = 5;
    public FeeAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (initialBalance - amount - fee >= 0) {
            initialBalance -= amount;
            totalTransactions++;
        } else {
            System.out.println("The amount of money in your account is not enough");
        }
    }
    public void endMonth() {
        initialBalance -= fee;
        super.endMonth();
    }
}


