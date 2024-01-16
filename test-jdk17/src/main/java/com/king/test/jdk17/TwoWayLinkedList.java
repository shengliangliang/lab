package com.king.test.jdk17;

public class TwoWayLinkedList {

    Node head;
    Node last;
    int length;
    public class Node{
        Integer value;
        Node prev;
        Node next;
        Node(Integer value,Node prev,Node next){
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    public TwoWayLinkedList(){
        this.head = new Node(null,null,null);
        this.last = new Node(null,null,null);
        length = 0;
    }

    public int getLength(){
        return length;
    }
    public boolean isEmpty(){
        return length==0;
    }
    public void add(Integer value){
        if(isEmpty()){
            Node newNode = new Node(value,null,null);
            last = newNode;
            head.next = last;
        }else{
            Node newNode = new Node(value,null,null);
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        length++;
    }
    public void printToLast(Node node){
        if(!isEmpty()){
            System.out.print(node.value);
            if(node.next!=null){
                System.out.print("<->");
                printToLast(node.next);
            }
        }
    }
    public void printToHead(Node node){
        if(!isEmpty()){
            System.out.print(node.value);
            if(node.prev!=null){
                System.out.print("<->");
                printToHead(node.prev);
            }
        }
    }

    public static void main(String[] args) {
        TwoWayLinkedList twoWayLinkedList = new TwoWayLinkedList();
        twoWayLinkedList.add(3);
        twoWayLinkedList.add(6);
        twoWayLinkedList.add(9);
        twoWayLinkedList.add(2);
        twoWayLinkedList.add(0);
        twoWayLinkedList.printToLast(twoWayLinkedList.head);
        System.out.println("\n");
        twoWayLinkedList.printToHead(twoWayLinkedList.last);
    }
}
