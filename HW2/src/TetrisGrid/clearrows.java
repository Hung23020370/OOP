public class ClearRows {
    public static boolean[][] clearRows(boolean[][] grid) {
        int rows = grid.length; // Số hàng của mảng
        int cols = grid[0].length; // Số cột của mảng
        for (int i = 0; i < rows; i++) {
            int cnt = 0;
            for (int j = 0; j < cols; j++) {
                if(grid[i][j]) cnt++;
            }
            if(cnt == cols) newGrid(grid,i,rows,cols);
        }
        return grid;
    }

    public static void newGrid(boolean[][] grid, int x, int rows, int cols) {
        for (int i = x; i < rows - 1; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = grid[i+1][j];
            }
        }
        if(x == rows - 1) {
            for (int j = 0; j < cols; j++) {
                grid[x][j] = false;
            }
        }

    }
}
