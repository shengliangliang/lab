package com.king.test.jdk17;

import java.util.ArrayList;
import java.util.List;

public class DirectoryPrint {
    static void print(TreeNode treeNode){

        if(treeNode==null){
            return;
        }
        System.out.println(treeNode.name);
        if(!treeNode.isDir){
            return;
        }
        List<TreeNode> children = treeNode.getChildren();
        if(children!=null&&children.size()>0){
            for(TreeNode child:children){
                print(child);
            }
        }
    }


    public static void main(String[] args) {
        TreeNode root =  new TreeNode("root");

        TreeNode tn1  = new TreeNode("tn1");

        TreeNode tn11  = new TreeNode("tn11");
        TreeNode tn12  = new TreeNode("tn12");
        TreeNode tn13  = new TreeNode("tn13");

        List<TreeNode> tn1Children = List.of(tn11,tn12,tn13);
        tn1.setDir(true);
        tn1.setChildren(tn1Children);

        TreeNode tn2  = new TreeNode("tn2");
        tn2.setDir(false);
        TreeNode tn3  = new TreeNode("tn3");
        tn3.setDir(false);

        List<TreeNode> children = List.of(tn1,tn2,tn3);
        root.setDir(true);
        root.setChildren(children);
        print(root);
    }

    static class TreeNode{
        String name;
        List<TreeNode> children;

        boolean isDir;
        public TreeNode(String name){
            this.name  = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode> children) {
            this.children = children;
        }

        public boolean isDir() {
            return isDir;
        }

        public void setDir(boolean dir) {
            isDir = dir;
        }
    }
}
