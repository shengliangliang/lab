package com.king.algorithm;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
public class ValidBracket {

    public static boolean isValid(String s){
        if(s.length()<=1){
            return false;
        }

        Map<String,String> bracketMap = Map.of(")","(","}","{","]","[",">","<");
        Set<String> stringSet = Set.of(")",">","}","]");
        if(stringSet.contains(String.valueOf(s.charAt(0)))){
            return false;
        }
        Stack<String> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            String s1 = String.valueOf(c);
            if(!stringSet.contains(s1)){
                stack.push(s1);
            }else {
                if(!stack.isEmpty()){
                    boolean b = bracketMap.get(s1).equals(stack.pop());
                    if(!b){
                        return b;
                    }
                }else{
                    return false;
                }
            }
        }
        System.out.println(stack);
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println(isValid("}"));
    }
}
