public class coutplus {
    public static void main(String[] args) {
        char [][] grid = {
                {' ','x','x','x',' ',' '},
                {' ','x','x','x',' ',' '},
                {'x','x','x','x','x',' '},
                {' ','x','x','x',' ',' '},
                {' ','x','x','x',' ',' '},
        };
        System.out.println(coutPlus(grid));
    }

    public static int coutPlus(char[][] grid) {
        int rows = grid.length; // Số hàng của mảng
        int cols = grid[0].length; // Số cột của mảng
        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] != ' ') {
                    char ch = grid[i][j];
                    if(findCenter(grid, ch, i, j, rows, cols)) cnt++;
                }
            }
        }
        return cnt;
    }

    public static boolean findCenter(char[][] grid, char ch, int i, int j, int rows, int cols) {
        int dis = 0;
        int i1 = i;
        // Kiểm tra điều kiện biên và điều kiện dừng
        while (i < rows && grid[i][j] == ch) {
            dis++;
            i++;
        }
        // Kiểm tra điều kiện để xác định điểm trung tâm
        if (dis % 2 == 1 && dis >= 5) {
            int iCenter = (i1 + i ) / 2; // Điều chỉnh vị trí của điểm trung tâm
            int jCenter = j;
            return extendCross(grid, ch, iCenter, jCenter, rows, cols, dis);
        }
        return false;
    }


    public static boolean extendCross(char[][] grid, char ch, int iCenter, int jCenter, int rows, int cols, int dis) {
        int cnt = 0;
        for(int j = jCenter + 1; j < cols; ++ j) {
            if(grid[iCenter][j] == ch) cnt++;
            else break;
        }
        if(cnt != (dis - 1) / 2) return false;
        cnt = 0;
        for(int j = jCenter - 1; j >= 0; -- j) {
            if(grid[iCenter][j] == ch) cnt++;
            else break;
        }
        if(cnt != (dis - 1) / 2) return false;
        return true;
    }
}
