package com.king.test.jdk17;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeTest {

    public static void main(String[] args) {
        Tree t = new Tree(3);
        t.put(2);
        t.put(5);
        t.put(1);
        t.put(0);
        t.put(4);
        t.put(6);

        t.midPrint(t.root);
        System.out.println("\r");
        System.out.println(t.root.value);
        System.out.println(t.isBalanced(t.root));
        System.out.println(t.isBalanced2(t.root));
        t.bfs(t.root);
    }




    static class Tree{
        Node root;
        Tree(Integer value){
            root = new Node(value);
        }
        Node root(){
            return root;
        }

        //使用bfs广度优先 对树进行层序遍历
        void bfs(Node root){
            if(root==null){
                return;
            }
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(root);
            while(!queue.isEmpty()){
                int size = queue.size();
                for(int i=0;i<size;i++){
                    Node node = queue.poll();
                    System.out.print(node.value);
                    if(node.left!=null){
                        queue.add(node.left);
                    }
                    if(node.right!=null){
                        queue.add(node.right);
                    }
                }
                System.out.println("\r");
            }
        }

        //自顶向下判断是否平衡 时间复杂度n的平方
        boolean isBalanced(Node root){
            if(root==null){
                return true;
            }
            return Math.abs(height(root.left)-height(root.right))<=1&&isBalanced(root.left)&&isBalanced(root.right);
        }
        private int height(Node node){
            if(node==null){
                return 0;
            }
            return Math.max(height(node.left),height(node.right))+1;
        }
        //自底向上遍历判断，有任何一个子树不是平衡二叉树，那么就不是平衡二叉树
        boolean isBalanced2(Node root){
            if(root==null){
                return true;
            }
            return height2(root)>=0;

        }
        private int height2(Node node){
            if(node==null){
                return 0;
            }
            /*Node left = node.left;
            Node right = node.right;
            if(height2(left)==-1||height2(right)==-1&&Math.abs(height2(left)-height2(right))>1){
                return -1;
            }
            return Math.max(height2(left),height2(right))+1;
            */
            int leftHeight = height2(node.left);
            int rightHeight = height2(node.right);
            if(leftHeight==-1||rightHeight==-1||Math.abs(leftHeight-rightHeight)>1){
                return -1;
            }
            return Math.max(leftHeight,rightHeight)+1;
        }

        void put(Integer value){
            if(root==null||root.value==null){
                root = new Node(value);
                return;
            }else{
                Node currentNode = root;
                Node newNode = new Node(value);
                while(true){
                    if(value>currentNode.value){
                        if(currentNode.right==null){
                            currentNode.right = newNode;
                            return;
                        }else{
                            currentNode = currentNode.right;
                        }
                    }else{
                        if(currentNode.left==null){
                            currentNode.left = newNode;
                            return;
                        }else{
                            currentNode = currentNode.left;
                        }
                    }
                }
            }


        }

        void midPrint(Node node){
            if(node.left!=null){
                midPrint(node.left);
            }
            System.out.print(node.value+",");
            if(node.right!=null){
                midPrint(node.right);
            }
        }

    }
    static class Node{
        Integer value;
        Node left;
        Node right;
        Node(Integer value){
            this.value = value;
        }

    }
}
