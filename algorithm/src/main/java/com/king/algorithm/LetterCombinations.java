package com.king.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinations {



    public static List<String> combination(String digits){

        Map<Integer,String> dictionary = new HashMap<>();

        dictionary.put(2, "abc");
        dictionary.put(3, "def");
        dictionary.put(4, "ghi");
        dictionary.put(5, "jkl");
        dictionary.put(6, "mno");
        dictionary.put(7, "pqrs");
        dictionary.put(8, "tuv");
        dictionary.put(9, "wxyz");

        char[] digitArray = digits.toCharArray();



        List<List<String>> combination = new ArrayList<>();

        for(int i=0;i<digitArray.length;i++){
            Integer c = Character.getNumericValue(digitArray[i]);
            String dic = dictionary.get(c);
            if(dic!=null){
                char[] dicArray = dic.toCharArray();
                List<String> strArray = new ArrayList<>();
                for(int j=0;j<dicArray.length;j++){
                    strArray.add(""+dicArray[j]);
                }
                combination.add(strArray);
            }
        }

        List<String> result = new ArrayList<>();
        for(int k=0;k<combination.size();k++){
            result = combination(result,combination.get(k));
        }

        System.out.println(combination);
        return result;
    }

    public static List<String> combination(List<String> list,List<String> params){
        if(list==null||list.size()==0){
            list.addAll(params);
            return list;
        }
        List<String> newList = new ArrayList<>();
        for(String s:list){
            for (String c:params) {
                String newS = s+c;
                newList.add(newS);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        System.out.println(combination("7391478"));
    }
}
