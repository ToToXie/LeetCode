package tags;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: LeetCode
 * @description: 精选面试题
 * @author: wd
 * @create: 2020-07-17 13:26
 **/

public class SelectedInterview {
    static int[][] intss = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};

    public static void main(String[] args) {
        SelectedInterview main = new SelectedInterview();
        main.setZeroes(intss);

    }

    static public void swap(int[][] nums, int x1, int y1, int x2, int y2) {
        if (x1 != x2 || y1 != y2) {
            int temp = nums[x1][y1];
            nums[x1][y1] = nums[x2][y2];
            nums[x2][y2] = temp;
        }
    }

    /**
     * 73 矩阵置零
     **/
    public void setZeroes(int[][] matrix) {
        Boolean isCol = false;
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < R; i++) {

            // Since first cell for both first row and first column is the same i.e. matrix[0][0]
            // We can use an additional variable for either the first row/column.
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if (matrix[i][0] == 0) {
                isCol = true;
            }

            for (int j = 1; j < C; j++) {
                // If an element is zero, we set the first element of the corresponding row and column to 0
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Iterate over the array once again and using the first row and first column, update the elements.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }

        // See if the first column needs to be set to zero as well
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 48 旋转图像
     **/
    public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int n = matrix.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                swap(matrix, i, j, n - 1 - j, n - 1 - i);
            }
        }
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                swap(matrix, i, j, n - 1 - i, j);
            }
        }
    }

    /**
     * 36 有效的数独
     **/
    public boolean isValidSudoku(char[][] board) {
        Set<Integer> setx = new HashSet<>();
        Set<Integer> sety = new HashSet<>();
        Set<Integer> setz = new HashSet<>();
        if (board == null) {
            return true;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Character.isDigit(board[i][j])) {
                    int val = board[i][j] - '0';
                    int x = (i + 1) * 9 + val;
                    int y = (j + 1) * 9 + val;
                    int z = (j / 3 + 3 * (i / 3) + 1) * 9 + val;
                    if (setx.contains(x) || sety.contains(y) || setz.contains(z)) {
                        return false;
                    } else {
                        setx.add(x);
                        sety.add(y);
                        setz.add(z);
                    }
                }
            }
        }
        return true;
    }
}
