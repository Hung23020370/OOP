import java.util.Scanner;

public class chararea {
    public static void main(String[] args) {
        char [][] grid = {
                {'c','a','x'},
                {'b',' ','b'},
                {' ',' ','a'}
        };
        Scanner sc = new Scanner(System.in);
        char ch = sc.next().charAt(0); // ki nag lay ki tu
        System.out.println(charArea(grid,ch));
    }

    private static int charArea(char[][] grid, char ch) {
        int rows = grid.length; // Số hàng của mảng
        int cols = grid[0].length; // Số cột của mảng
        int min_length = cols;
        int max_length = 0;
        int min_width = rows;
        int max_width = 0;
        for(int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == ch) {
                    min_width = Math.min(min_width,j);
                    max_width = Math.max(max_length,j);
                    min_length = Math.min(min_length,i);
                    max_length = Math.max(max_length,i);
                }
            }
        }
        if(min_width == rows ) return 0;
        return (max_width - min_width + 1) * (max_length - min_length + 1);
    }
}
