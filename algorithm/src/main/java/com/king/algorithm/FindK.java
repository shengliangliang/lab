package com.king.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法：
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。1 <= n <= 20，1 <= k <= n。
 * 你可以按 任何顺序 返回答案。
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 *
 */

public class FindK {

    private static List<List<Integer>> findK(List<Integer> resource, int k) {
        System.out.println(resource);
        int length = resource.size();
        //传入result 收集结果，
        List<List<Integer>> result = new ArrayList<>();
        //初始化中间集合用来暂存组合
        List<Integer> middle = new ArrayList<Integer>();
        find(0, k, result, middle,resource);
        return result;
    }

    static void find(int i, int k, List<List<Integer>> result, List<Integer> middle,List<Integer> resource) {
        if (k == 0) {//终止条件，此时middle选择了7个数
            //收集可用的组合
            result.add(new ArrayList<>(middle));
            return ;
        }
        for (int j = i; j < resource.size(); j++) {
            //添加元素到中间集合，获取resource中的第j个元素
            Integer ele = resource.get(j);
            middle.add(ele);
            find(j+1, k-1, result, middle,resource);
            middle.remove(middle.size() - 1);
        }

    }


    public static void main(String[] args) {

        //都用list表示，list中是1到n的连续正整数
        List<List<Integer>> result = findK(List.of(1,2,3,4,5,6,7,8,9,10),7);

        System.out.println(result);
    }
}
