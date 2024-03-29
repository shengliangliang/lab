package com.king.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 */
public class GenerateParenthesis {

    /**
     * 暴力解法，穷举，然后剪枝，删除不合规的组合
     * @param n
     * @return
     */
    static List<String> generate(int n){
        if(n<1){
            return List.of();
        }
        if(n==1){
            return List.of("()");
        }
        List<String> combination = new ArrayList<>();
        generateAll(new char[n*2],0,combination);
        return combination;
    }

    static void generateAll(char[] current,int position,List<String> combination){
        if(current.length==position){
            if(valid(current)){//不合规的不放入结果集合
                combination.add(new String(current));
            }
        }else{
            current[position] = '(';
            generateAll(current,position+1,combination);
            current[position] = ')';
            generateAll(current,position+1,combination);
        }
    }

    private static boolean valid(char[] current) {
        int balance = 0;
        for (char c:current){
            if('('==c){
                balance++;
            }else{
                balance--;
            }
            if(balance<0){ //如果小于0，说明有右括号没有被匹配。
                return false;
            }
        }
        return balance==0;
    }


    public static void main(String[] args) {
        System.out.println(generate(10));
        System.out.println(generateParenthesis(10));
    }


    /**
     * 回溯解法，时间复杂度降低
     * @param n
     * @return
     */
    static List<String> generateParenthesis(int n){
        List<String> combination = new ArrayList<>();

        backTrack(new StringBuilder(),0,0,n,combination);

        return combination;
    }

    //open 表示左括号数量，close表示右括号数量
    static void backTrack(StringBuilder current,int open,int close,int max,List<String> combination){
        if(current.length()==max*2){
            combination.add(current.toString());
        }
        if(open<max){
            current.append('(');
            backTrack(current,open+1,close,max,combination);
            System.out.println(current.charAt(current.length()-1));
            current.deleteCharAt(current.length()-1);

        }
        if(close<open){
            current.append(')');
            backTrack(current,open,close+1,max,combination);
            System.out.println(current.charAt(current.length()-1));
            current.deleteCharAt(current.length()-1);
        }
    }
}
