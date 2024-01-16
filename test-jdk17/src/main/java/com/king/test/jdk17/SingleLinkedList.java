package com.king.test.jdk17;

public class SingleLinkedList {

    //单链表的倒数第n个元素
    private static Node findLastNNode(LinkedList linkedList,int n){
        if(n>linkedList.length){
            return null;
        }

        Node head = linkedList.head;
        Node leftNode = head;
        Node rightNode = head.next;
        for(int i=1;i<n;i++){
            Node node = rightNode.next;
            rightNode = node;
        }
        for(int i=0;i+n<linkedList.length;i++){
            Node l = leftNode.next;
            leftNode = l;
            Node r = rightNode.next;
            rightNode = r;
        }

        return leftNode;
    }
    public static void main(String[] args) {
        LinkedList linked = new LinkedList("A");
        linked.add("B");
        linked.add("c");
        linked.add("d");
        linked.add("e");
        Node node = linked.head;
        while(node != null){
            System.out.println(node.value);
            node = node.next;
        }

        Node n = findLastNNode(linked,4);
        System.out.println(n.value);
    }

    static class  LinkedList{
        Node head;
        Node tail;
        int length;
        LinkedList(String value){
            Node node = new Node(value);
            if(this.head==null){
                this.head = node;
                this.tail = new Node("");
                this.head.next = this.tail;
            }

            length = 1;
        }

        void add(String value){

            Node curr = this.tail;

            if(curr.value==""){
                curr.value = value;
                this.tail = new Node("");
                curr.next  = this.tail;

            }
            length = length+1;
        }

    }

    static class Node{
        String value;
        Node next;
        Node(String value){
            this.value = value;
        };
    }
}
