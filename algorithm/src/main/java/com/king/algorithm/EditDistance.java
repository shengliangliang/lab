package com.king.algorithm;



public class EditDistance {

    //递归解法
    public  static int minDistance2(String word1, String word2) {
        if(word1.length()*word2.length()==0){
            return Math.max(word1.length(),word2.length());
        }
        if(word1.substring(word1.length()-1).equals(word2.substring(word2.length()-1))){
            System.out.println("word1:"+word1.substring(0,word1.length()-1)+",word2:"+word2.substring(0,word2.length()-1));
            return minDistance2(word1.substring(0,word1.length()-1),word2.substring(0,word2.length()-1));
        }

        return
                Math.min(
                    Math.min(minDistance2(word1.substring(0,word1.length()-1),word2),
                            minDistance2(word1,word2.substring(0,word2.length()-1))),
                        minDistance2(word1.substring(0,word1.length()-1),word2.substring(0,word2.length()-1)))
                ;
    }

    //动态规划解法
        public  static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    public static void main(String[] args) {
        String a = "abcde";
        System.out.println(a.substring(a.length()-1));
        System.out.println(minDistance2("abcde","afese"));
        System.out.println(minDistance("abcde","afese"));

    }
}
