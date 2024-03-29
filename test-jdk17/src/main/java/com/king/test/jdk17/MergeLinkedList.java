package com.king.test.jdk17;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


// 合并两个或者K个升序链表
public class MergeLinkedList {
    static ListNode merge(List<ListNode> nLinkedList){
        ListNode head = nLinkedList.get(0);
        for(int i=1;i<nLinkedList.size();i++){
            ListNode listNode = nLinkedList.get(i);
            head = mergeTwo(head,listNode);
        }
        return head;
    }

    // 递归的方式合并
    static ListNode mergeTwo(ListNode list1,ListNode list2){

        if(list1==null){
            return list2;
        }else if(list2==null){
            return list1;
        }else if(list1.val<list2.val){
            list1.next = mergeTwo(list1.next,list2);
            return list1;
        }else{
            list2.next = mergeTwo(list1,list2.next);
            return list2;
        }
    }

    //迭代的方式合并
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(3);
        listNode1.next = new ListNode(5);
        listNode1.next.next = new ListNode(9);


        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(4);
        listNode2.next.next = new ListNode(7);

        ListNode listNode3 = new ListNode(2);
        listNode3.next = new ListNode(6);
        listNode3.next.next = new ListNode(9);
        List<ListNode> list = List.of(listNode1,listNode2,listNode3);

        /*ListNode node = merge(list);
        while(node.next!=null){
            System.out.println(node.val);
            node = node.next;
        }*/

        ListNode head = mergeKList(list);
        while(head.next!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }


    //直接初始化优先队列，实现比较方法
    static PriorityQueue<ListNode> pq = new PriorityQueue<>((o1,o2)->{return o1.val-o2.val;});

    //使用优先队列合并
    static ListNode mergeKList(List<ListNode> list){

        for(ListNode listNode:list){
            //ListNode head = listNode;
            if(listNode!=null){
                pq.offer(listNode);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;
            node = node.next;
            if(node!=null){
                pq.offer(node);
            }
        }
        return dummy;
    }
    static class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }

}

/*
*/
