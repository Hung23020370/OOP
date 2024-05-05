import java.util.Random;

public class Gambler extends BankAccount{

    public Gambler(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        Random random = new Random();
        double probability = random.nextDouble();
        if (probability > 0.49) {
            if(initialBalance - amount * 2 >= 0) {
                initialBalance -= amount * 2;
                totalTransactions ++;
            }
            else {
                System.out.println("The amount of money in your account is not enough");
            }
        }
    }
}
