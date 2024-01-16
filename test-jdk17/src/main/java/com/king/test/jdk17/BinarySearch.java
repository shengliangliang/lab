package com.king.test.jdk17;
//有重复元素的二分查找，返回重复元素的第一个位置。输入： [1,2,3,3,3,3,4,5] 3, 输出：  2
public class BinarySearch {

    private static int search(int[] arr,int target){
        int low = 0;
        int high = arr.length-1;
        while(low<high){
            int mid = (low+high)/2;
            if(arr[mid]==target){
                return mid;
            } else if (arr[mid]>target) {
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return -1;
    }

    private static int searchLeft(int[] arr,int target){
        int low = 0;
        int high = arr.length-1;
        while(low<high){
            int mid = (low+high)/2;
            if(arr[mid]>=target){
                //保留右边值，万一是从左数第一个呢
                high = mid;
            }else{
                low = low + 1;
            }
        }
        if(arr[low]==target){
            return low;
        }
        return -1;
    }
    private static int searchRight(int[] arr,int target){
        int low = 0;
        int high = arr.length-1;
        while(low<high){
            int mid = (low+high)/2+1;
            if(arr[mid]<=target){
                low = mid;
            }else{
                high = mid-1;
            }
        }
        if(arr[high]==target){
            return high;
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println("binSearch:"+search(new int[]{1,2,3,3,3,3,4,5},3));
        System.out.println("left:"+searchLeft(new int[]{1,2,3,3,3,3,4,5},3));
        System.out.println("right:"+searchRight(new int[]{1,2,3,3,3,3,4,5},3));
    }
}
