package com.king.algorithm;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 盛最多水的容器
public class MaxArea {

    public static int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,4,5,2,1,6,8,2,4,8,3,9,3,1,6,5,3,2};
        System.out.println(maxArea(array));
        System.out.println(maxArea2(array));

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(0);
        list.add(1);
        list.sort((o1,o2)->o1.compareTo(o2));
        
        System.out.println(list);
    }

    public static int maxArea2(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

}
