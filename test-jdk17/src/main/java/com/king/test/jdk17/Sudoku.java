package com.king.test.jdk17;

public class Sudoku {

    //请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
    //
    //数字 1-9 在每一行只能出现一次。
    //数字 1-9 在每一列只能出现一次。
    //数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
    //
    //
    //注意：
    //
    //一个有效的数独（部分已被填充）不一定是可解的。
    //只需要根据以上规则，验证已经填入的数字是否有效即可。
    //空白格用 '.' 表示。
    //示例
    //输入：board =
    //[["5","3",".",".","7",".",".",".","."]
    //,["6",".",".","1","9","5",".",".","."]
    //,[".","9","8",".",".",".",".","6","."]
    //,["8",".",".",".","6",".",".",".","3"]
    //,["4",".",".","8",".","3",".",".","1"]
    //,["7",".",".",".","2",".",".",".","6"]
    //,[".","6",".",".",".",".","2","8","."]
    //,[".",".",".","4","1","9",".",".","5"]
    //,[".",".",".",".","8",".",".","7","9"]]
    //输出：true
    public static boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValidSudoku(new char[][]{{3,5,8,9},{1,4,6,3}}));
    }
}
