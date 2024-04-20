import java.util.Scanner;

public class maxRun {
    public static int maxrun(String s) {
        int n = s.length();
        char[] a = s.toCharArray(); // biến string thành char
        int cnt = 1;
        int maxsp = 0;
        for (int i = 0; i < n - 1; i++) {
            if(a[i] == a[i + 1]) cnt++;
            else {
                if(cnt > maxsp) maxsp = cnt;
                cnt = 1;
            }
        }
        return maxsp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(maxrun(s));
    }
}
