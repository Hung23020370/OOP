import java.util.Arrays;
import java.util.Scanner;

public class B15 {
    public static boolean isCircularShift(String s, String t) {
        String s1 = s + s;
        return s1.contains(t);
    }

    public static void main(String[] args) {
        String s,t;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        t =sc.nextLine();
        System.out.println(isCircularShift(s,t));
    }
}
