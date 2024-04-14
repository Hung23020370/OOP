import java.util.Scanner;

public class B9 {
    private static boolean isValidDNA(String s) {
        char[] a = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if(a[i] != 'A' && a[i] != 'T' && a[i] != 'C' && a[i] == 'G')
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.print(isValidDNA(s));
    }
}
