package com.king.test.jdk17;

public class Regular {
    /*给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

     '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素
    所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     */
    public static boolean isMatch(String string,String pattern){
        int m = string.length();
        int n = pattern.length();

        boolean[][] f = new boolean[m+1][n+1];
        f[0][0] = true;
        for(int i=0;i<=m;i++){
            for(int j=1;j<n;j++) {
                if(pattern.charAt(j-1) == '*'){
                    f[i][j] = f[i][j-2];
                    if(matches(string,pattern,i,j-1)){
                        f[i][j] = f[i][j] || f[i-1][j];
                    }
                }else{
                    if(matches(string,pattern,i,j)){
                        f[i][j]  = f[i][j-1];
                    }
                }
                System.out.println(f[i][j]);
            }
        }

        return f[m][n];
    }


    public  static boolean matches(String s,String p,int i,int j){
        if(i==0){
            return false;
        }
        if(p.charAt(j-1)=='.'){
            return true;
        }
        return s.charAt(i-1)==p.charAt(j-1);
    }

    public static void main(String[] args) {
        System.out.println(isMatch("sdfdsrg","s*"));
    }
}
