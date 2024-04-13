import java.util.Scanner;

public class B10 {
    public static String complementWatsonCrick(String s) {
        char[] a = s.toCharArray();
        for(int i = 0; i < s.length(); ++i) {
            if (a[i] == 'A') a[i] = 'T';
            else if (a[i] == 'T') a[i] = 'A';
            else if (a[i] == 'X') a[i] = 'G';
            else if (a[i] == 'G') a[i] = 'X';
        }
        return new String(a);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s = complementWatsonCrick(s);
        System.out.println(s);
    }
}
