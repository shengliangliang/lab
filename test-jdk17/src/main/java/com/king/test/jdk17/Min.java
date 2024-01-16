package com.king.test.jdk17;

//给一个字符串整数，去掉N位以后，找到最小整数
public class Min {

    public static void main(String[] args) {
        String a = "678917263494716825";
        System.out.println(min(a.toCharArray(),10));
    }
    //static char[] finalChars ;
    static int choose;
    public static char[] min(char[] chars,int n){
        int finalLength = chars.length-n;
        System.out.println("charLength:"+chars.length+",finalLength:"+finalLength);
        char[] finalChars = new char[finalLength];
        int lastIndex = 0;
        for(int i=0;i<finalChars.length;i++){
            int index = findIndex(chars,lastIndex+1, chars.length, finalLength);
            lastIndex = index;
            finalChars[i] = chars[index];
            choose = i;
        }

        return finalChars;
    }

    public static int findIndex(char[] chars,int begin,int end,int finalLength){
        int index = 0;
        int min = Integer.MAX_VALUE;
        for(int i=begin;i<end;i++){
            if((end+1-i)+choose>=finalLength&&chars[i]<min){
                min = chars[i];
                index = i;
                System.out.println(i);
            }
        }
        return index;
    }
//12341682
//12344168
//12316825

}
