import java.util.Random;

public abstract class BankAccount {
    protected double initialBalance;
    protected int totalTransactions;

    public BankAccount(double initialBalance) {
        this.initialBalance = initialBalance;
        this.totalTransactions = 0;
    }

    public void deposit(double amount) {
        initialBalance += amount;
        totalTransactions++;
    }

    public abstract void withdraw(double amount);

    public void endMonth() {
        // This method should be overridden by subclasses
        System.out.println("End of Month Summary:");
        System.out.println("Total transactions: " + totalTransactions);
        System.out.println("Current balance: " + initialBalance);
        totalTransactions = 0;
    }

    public double getBalance() {
        return initialBalance;
    }
}