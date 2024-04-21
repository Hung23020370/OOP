import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class stringintersect {
    public static boolean stringIntersect(String s1, String s2, int len) {
        HashSet <String> hss = new HashSet<>();
        for (int i = 0; i < s1.length() - len; i++) {
            String s3 = s1.substring(i, i + len);
            hss.add(s3);
        }
        for (int i = 0; i < s2.length() - len; i++) {
            String s3 = s2.substring(i, i + len);
            if(hss.contains(s3)) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int len = sc.nextInt();
        if(stringIntersect(s1,s2,len)) System.out.println("YES");
        else System.out.println("NO");
    }
}
