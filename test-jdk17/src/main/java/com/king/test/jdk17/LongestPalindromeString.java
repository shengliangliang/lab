package com.king.test.jdk17;

/***
 * 最长回文子串
 */
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
                        longestSub = sub;
                    }
                }
            }
        }

        return longestSub;
    }

    public static boolean isPalindrome(char[] chars){
        int length = chars.length;
        if(chars.length==0)return false;
        if(chars.length==1)return true;
        int mid = 0;
        if(length/2>0){
            mid = length/2;
        }/*else{
            mid = length/2-1;
        }*/
        for(int i=0;i<=mid;i++){
            if(chars[i] != chars[length-1-i]){
                return false;
            }
        }
        return true;
    }
    //最长回文子串-动态规划解法
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



    public static void main(String[] args){
        System.out.println(isPalindrome(new char[]{'A','A','B','A','A'}));
        System.out.println(longestPalindrome("fghjklkkkldf"));
        System.out.println(longestPalindrome2("fghjklkkkldf"));
        System.out.println(0-'0');
    }

}
