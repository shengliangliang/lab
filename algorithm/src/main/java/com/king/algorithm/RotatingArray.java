package com.king.algorithm;


/***  搜索旋转数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class RotatingArray {

    public static int search(Integer[] array , Integer target){
        int length = array.length;
        int left = 0,right=length-1;

        while(left<right){
            int mid = (left+right)/2;
            System.out.println("left:"+left+",right:"+right+",mid:"+mid);
            if(array[mid]==target){
                return mid;
            }
            if(array[0]>array[mid]){ //如果首位元素大于中位元素，说明左半边包含旋转点。
                if(array[0]>target&&array[mid]>target){//说明在目标在 数组左半边的 右半部分
                    right = mid-1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(array[left]>target&&array[mid]>target){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }

        }
        return -1;
    }


    public static void main(String[] args) {
        Integer[] array = new Integer[]{4,5,6,7,8,0,1,2,3};
        System.out.println(search(array,5));
    }
}
