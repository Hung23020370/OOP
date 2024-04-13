import java.util.Scanner;

public class B1 {
    private static String reserve(String s) {
        char[] a = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n/2; i++) {
            char temp = a[i];
            a[i] = a[n-i-1];
            a[n-i-1] = temp;
        }
        return new String(a);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.print(reserve(s));
    }
}