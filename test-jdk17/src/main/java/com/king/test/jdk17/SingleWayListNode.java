package com.king.test.jdk17;

public class SingleWayListNode {
    Integer value;
    SingleWayListNode next;

    SingleWayListNode(int v){
        this.value = v;
    }
    public void add(int value){
        SingleWayListNode newNode = new SingleWayListNode(value);
        if(this.next == null){
            this.next = newNode;
        }else{
            this.next.add(value);
        }
    }
    public void print(){
        System.out.print(this.value);
        if(this.next!=null){
            System.out.print("->");
            this.next.print();
        }
    }
}
