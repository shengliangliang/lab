package com.king.test.jdk17;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTest {


    List<Integer> arr = new ArrayList<>();

    private static void recursion(Node node){
        if(node==null){
            return;
        }
        Node leftChild = node.getLeft();
        Node rightChild = node.getRight();

        recursion(leftChild);
        System.out.print(node.getVal()+",");
        recursion(rightChild);
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(3);
        tree.insert(4);
        tree.insert(1);
        tree.insert(0);
        tree.insert(8);
        tree.insert(7);
        tree.insert(9);
        tree.insert(2);
        tree.insert(6);
        tree.insert(5);
        tree.insert(11);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);
        tree.insert(15);
        tree.insert(16);
        tree.insert(17);
        tree.insert(18);
        tree.insert(19);
        tree.insert(20);

        tree.preList();
        System.out.println("\r");
        tree.inList();
        System.out.println("\r");
        tree.postList();
        System.out.println("\r");
        tree.show(tree.root);

        //System.out.println(tree.find(5));
    }

}

class BinarySearchTree{
    Node root;

    public void show(Node node) {
        if (node == null)
            System.out.println("EMPTY!");
        node = root;
        // 得到树的深度
        int treeDepth = getTreeDepth(node);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(node, 0, arrayWidth/ 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }
    // 用于获得树的层数
    public static int getTreeDepth(Node root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.getLeft()), getTreeDepth(root.getRight())));
    }


    private static void writeArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.getVal());

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.getLeft() != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.getLeft(), rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.getRight() != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.getRight(), rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }
    public Node find(int value){
        Node current;
        current = root;
        while(current!=null){
            if(value<current.getVal()){
                current = current.getLeft();
            }else if(value>current.getVal()){
                current = current.getRight();
            }else if(value == current.getVal()){
                return current;
            }
        }
        return null;
    }
    public void insert(int val){
        Node node = new Node(val);
        Node current ;
        if(root==null){
            root = node;
        }else{
            current = root;
            while(true){
                if(val>current.getVal()){
                    if(current.getRight()==null){
                        current.setRight(node);
                        return;
                    }else{
                        current = current.getRight();
                    }
                }else{
                    if(current.getLeft()==null){
                        current.setLeft(node);
                        return;
                    }else{
                        current = current.getLeft();
                    }
                }
            }

        }

    };
    public void preList(){
        preOrder(root);
    }
    public void inList(){
        inOrder(root);
    }
    public void postList(){
        postOrder(root);
    }
    public void preOrder(Node node){
        if(node==null){
            return;
        }
        Node leftChild = node.getLeft();
        Node rightChild = node.getRight();
        System.out.print(node.getVal()+",");
        preOrder(leftChild);
        preOrder(rightChild);
    }
    public void inOrder(Node node){
        if(node==null){
            return;
        }
        Node leftChild = node.getLeft();
        Node rightChild = node.getRight();

        inOrder(leftChild);
        System.out.print(node.getVal()+",");
        inOrder(rightChild);
    }
    public void postOrder(Node node){
        if(node==null){
            return;
        }
        Node leftChild = node.getLeft();
        Node rightChild = node.getRight();

        postOrder(leftChild);
        postOrder(rightChild);
        System.out.print(node.getVal()+",");
    }
}
class Node{
    private Node left;
    private Node right;
    private Integer val;

    public Node(Integer val){
        this.val = val;
    }
    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
