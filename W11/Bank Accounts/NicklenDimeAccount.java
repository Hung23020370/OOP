public class NicklenDimeAccount extends BankAccount {
    private static final double fee = 0.5;
    public NicklenDimeAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (initialBalance - amount - fee*(totalTransactions + 1) >= 0) {
            initialBalance -= amount;
            totalTransactions++;
        } else {
            System.out.println("The amount of money in your account is not enough");
        }
    }
}
