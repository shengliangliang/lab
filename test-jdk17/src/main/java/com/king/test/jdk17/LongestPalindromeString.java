package com.king.test.jdk17;


public class LongestPalindromeString {

    //中心点法
    public static String longestPalindrome2(String s) {
        int length = s.length();
        if(length<2){
            return s;
        }

        String longestSub = "";
        for(int i=0;i < length;i++){
            for(int j=i+1;j<=length;j++){
                String sub  = s.substring(i,j);
                if(isPalindrome(sub.toCharArray())){
                    if(sub.length()>longestSub.length()){
                        longestSub = s.substring(i,j);
                    }
                }
            }
        }

        return longestSub;
    }

    public static boolean isPalindrome(char[] chars){
        int length = chars.length;

        int mid = 0;
        if(length/2>0){
            mid = length/2;
        }else{
            mid = length/2-1;
        }
        for(int i=0;i<=mid;i++){
            if(chars[i] != chars[length-1-i]){
                return false;
            }
        }
        return true;
    }
    //最长回文子串
    public static String longestPalindrome(String s) {
        //动态规划解法
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 R - i + 1 = L 得
                int R = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (R >= len) {
                    break;
                }

                if (charArray[i] != charArray[R]) {
                    dp[i][R] = false;
                } else {
                    if (R - i < 3) {
                        dp[i][R] = true;
                    } else {
                        dp[i][R] = dp[i + 1][R - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][R] && R - i + 1 > maxLen) {
                    maxLen = R - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    //将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
    //比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
    //P   A   H   N
    //A P L S I I G
    //Y   I   R
    //之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
    //请你实现这个将字符串进行指定行数变换的函数：
    //string convert(string s, int numRows);
    //示例
    //输入：s = "PAYPALISHIRING", numRows = 3
    //输出："PAHNAPLSIIGYIR"
    //思路：二维数组矩阵，填数，然后遍历
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }

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
    public boolean isValidSudoku(char[][] board) {
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

    public static void main(String[] args){
        System.out.println(isPalindrome(new char[]{'A','A','B','A','A'}));
        System.out.println(longestPalindrome("fghjklkkkldf"));
        System.out.println(longestPalindrome2("fghjklkkkldf"));
        System.out.println(0-'0');
    }

}
