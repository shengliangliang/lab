package com.king.algorithm;

import java.util.Stack;

public class ReverseSingleLinkedList {

    static Node reverse(Node head){
        //使用栈

        Node current = head;

        Stack<Node> stack = new Stack<>();
        //Node newHead = null ;
        while(current!=null){
            stack.push(current);
            //newHead = current;
            current = current.next;
        }

        Node newHead = stack.pop();
        current = newHead;
        while (!stack.isEmpty()){
            Node node = stack.pop();
            current.next = node;
            current = node;
        }
        current.next = null;
        return newHead;
    }

    static Node reverse2(Node head){
        //迭代的方式
        Node prev = null;
        Node current = head;

        while (current!=null){
            Node next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }
        return prev;
    }

    static Node reverse3(Node head){
        //递归的方式
        Node prev = null;
        Node current = head;

        while (current!=null){
            Node next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }
        return prev;
    }


    public static void main(String[] args) {
        Node head = new Node();
        head.setValue(0);

        Node curr = head;
        for(int i=1;i<5;i++){
            Node node = new Node();
            node.setValue(i);
            curr.next = node;
            curr = node;
        }


        curr = head;
        while(curr!=null){
            System.out.println(curr.value);
            curr = curr.getNext();
        }

        //Node newHead = reverse(head);
        Node newHead = reverse2(head);


        while (newHead!=null){
            System.out.println(newHead.value);
            newHead = newHead.next;
        }
    }
}




class Node{
    int  value;
    Node next;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

