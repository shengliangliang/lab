package com.king.test.jdk17;

public class MinDepthOfTree {
    
    static int minDepth(TreeNode treeNode){
        if(treeNode==null){
            return 0;
        }
        if(treeNode.left==null && treeNode.right==null){
            return 1;
        }

        int minDepth =  Integer.MAX_VALUE;

        minDepth = Math.min(minDepth(treeNode.getLeft()),minDepth);
        minDepth = Math.min(minDepth(treeNode.getRight()),minDepth);

        return minDepth+1;
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

        System.out.println(minDepth(treeNode));
    }


    static  class TreeNode{
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


