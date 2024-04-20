import java.util.Scanner;

public class String_Blowup {
    public  static String string_blowup(String s) {
        int n = s.length();
        char[] a = s.toCharArray(); // biến string thành char
        String s1 = "";
        for(int i = 0; i < n -1; ++i) {
            // hàm check xem kí tự có phai số nguyên hay không
            if(Character.isDigit(a[i])){
                int number = a[i] - '0';
                for(int j = 0; j < number; ++j) {
                    s1 += a[i+1];
                }
            }
            else s1 += a[i];
        }
        if(!Character.isDigit(a[n-1])) s1 += a[n-1];
        return s1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s = string_blowup(s);
        System.out.println(s);
    }
}
