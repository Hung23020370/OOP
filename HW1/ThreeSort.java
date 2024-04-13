import java.util.Random;

public class ThreeSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int a = rand.nextInt();
        int b = rand.nextInt();
        int c = rand.nextInt();
        System.out.println(a + " " + b + " " + c);
        int max = Math.max(a,Math.max(b,c));
        int min = Math.min(a,Math.min(b,c));
        int mid = a +b +c - max - min;
        System.out.print(min + " " + mid + " " + max);
    }
}
