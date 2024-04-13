import java.util.Arrays;
import java.util.Random;

public class Stats5 {
    public static void main(String[] args) {
        double [] number = new double[5];
        double sum = 0;
        double max = 0;
        double min = 1;
        for(int i = 0; i < 5; i++) {
            number[i] = Math.random();
            System.out.print(number[i] + "\t");
            sum += number[i];
            min = Math.min(min,number[i]);
            max = Math.max(max,number[i]);
        }
        System.out.println();
        System.out.println(sum/5);
        System.out.println(max);
        System.out.println(min);
    }
}
