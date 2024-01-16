package com.king.test.jdk17;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        //找出两个数之和等于目标对象
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            int ft = target-nums[i];
            Integer index = map.get(ft);
            if(index!=null&&index!=i){
                return new int[]{i,index};
            }
        }
        return null;
    }

    public static SingleWayListNode addTwoNumbers(SingleWayListNode l1, SingleWayListNode l2) {
        int carry = 0;
        SingleWayListNode newListNode = null;
        SingleWayListNode tempNode1 = l1;
        SingleWayListNode tempNode2 = l2;
        while(tempNode1!=null||tempNode2!=null){
            int res = 0;
            if(tempNode1!=null&&tempNode1.value!=null){
                res += tempNode1.value;
            }
            if(tempNode2!=null&&tempNode2.value!=null){
                res += tempNode2.value;
            }
            res += carry;

            int value = 0;
            if(res>=10){
                carry = 1;
                value = res - 10;
            }else{
                carry = 0;
                value = res;
            }

            if(newListNode == null){
                newListNode = new SingleWayListNode(value);
            }else{
                newListNode.add(value);
            }
            if(tempNode1!=null&&tempNode1.next!=null){
                tempNode1 = tempNode1.next;
            }else{
                tempNode1 = null;
            }

            if(tempNode2!=null&&tempNode2.next!=null){
                tempNode2 = tempNode2.next;
            }else{
                tempNode2 = null;
            }
        }
        if (carry>0){
            newListNode.add(1);
        }
        return newListNode;
    }
    public static void main(String[] args){
        int[] nums = new int[]{3,2,4};
        int[] r = twoSum(nums,6);
        for(Integer i:r){
            System.out.println(i);
        }

        SingleWayListNode listA = new SingleWayListNode(5);
        listA.add(6);
        listA.add(7);
        listA.add(3);
        listA.add(2);
        listA.print();
        System.out.println("\n---");
        SingleWayListNode listB = new SingleWayListNode(7);
        listB.add(7);
        listB.add(3);
        listB.add(6);
        listB.print();
        System.out.println("\n---");
        addTwoNumbers(listA,listB).print();
    }
}


