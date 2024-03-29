package com.king.test.jdk17;

import java.util.LinkedList;
import java.util.Queue;

// 获取树的最大深度
public class MaxDepthOfTree {

    // 1,递归方式获取 dfs遍历
    static int maxDepth(TreeNode treeNode){
        if(treeNode==null){
            return 0;
        }
        TreeNode left = treeNode.left;
        TreeNode right = treeNode.right;

        return Math.max(maxDepth(treeNode.left),maxDepth(treeNode.right))+1;
    }

    //2,优先队列方式获取 bfs遍历
    static int maxDepth2(TreeNode treeNode){

        if(treeNode==null){
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(treeNode);

        int depth = 0;
        while (!q.isEmpty()){
           //TreeNode node = q.poll();
           int size = q.size();
           for(int i=0;i<size;i++){
               TreeNode node = q.poll();

               TreeNode left =  node.getLeft();
               if(left!=null){
                   q.offer(left);
               }
               TreeNode right =  node.getLeft();
               if(right!=null){
                   q.offer(right);
               }

           }
            depth ++;
        }

        return depth;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);

        treeNode.setLeft(treeNode1);
        treeNode.setRight(treeNode2);

        treeNode1.setLeft(treeNode3);
        treeNode1.setRight(treeNode4);

        treeNode2.setLeft(treeNode5);

        System.out.println(maxDepth(treeNode));
        System.out.println(maxDepth2(treeNode));

    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
}



