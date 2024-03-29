package com.king.test.jdk17;

public class FindMaxNum {

    static int left = 0;
    static int right = 0;

    public static void main(String[] args){
        Integer[] arr = new Integer[]{1,2,5,5,6,7,8,8,8,9,9,9,9,9,9,9,10,10,12,12,7,6,5,5,4,4,2,1};
        Integer max = Integer.MIN_VALUE;

        int low = 0;
        int high = arr.length-1;
        int index = (low+high)/2;


        while(low<high){
            System.out.println("index:"+index);
            if(arr[index]>max){
                max = arr[index];
                System.out.println("curr max:"+max);
                int i = findBiggerSideIndex(index,arr);
                if(i>index){
                    low++;
                }else if(i==index){
                    break;
                }else{
                    high--;
                }
                /*if(isBiggestNum(index,arr)){
                    break;
                }else{
                    if(newIndex>index){
                        low++;
                    }else{
                        high--;
                    }
                }*/
            }
            if(arr[index]==max){
                int i = findBiggerSideIndex(index,arr);
                if(i>index){
                    low++;
                }else{
                    high--;
                }
            }
            System.out.println("low:"+low);
            if(arr[low]<max){
                low++;
            }
            if(arr[high]<max){
                high--;
            }
            index = (low+high)/2;
        }

        System.out.println("max:"+max+", count:"+(right-left-1));
    }


    public static int findBiggerSideIndex(int index,Integer[] arr){

        left = index-1;
        right = index+1;
        for(int i=0;i<arr.length;i++){

            if(arr[left]==arr[index]){
                left--;
            }
            if(arr[right]==arr[index]){
                right++;
            }
            if(arr[left]>arr[index]){
                return left;
            }
            if(arr[right]>arr[index]){
                return right;

            }
            if(arr[left]<arr[index]&&arr[right]<arr[index]){
                return index;
            }
        }
        return index;
    }
}
