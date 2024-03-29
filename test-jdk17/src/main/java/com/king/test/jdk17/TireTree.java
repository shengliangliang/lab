package com.king.test.jdk17;

import java.util.ArrayList;
import java.util.List;

public class TireTree {



    public static void main(String[] args) {
        Tire tire = new Tire(' ',null);
        tire.insert("slsdf");
        tire.insert("abhdb");
        tire.insert("abty");
        tire.insert("abpoy");


        List<String> list = new ArrayList<>();
        list.add("-");
        tire.print(list,tire.root);
        System.out.println(list);

        System.out.println(tire.startWith("ab"));
    }

    static class Tire{
        Node root;

        Tire(char c, Node parent){
            root = new Node(c,parent);
            root.setChildren(new ArrayList<>());
        }
        boolean insert(String string){
            char[] chars = string.toCharArray();
            if(root==null){
                root = new Node(' ',null);
                root.setChildren(new ArrayList<>());
            }
            List<Node> children = root.getChildren();
            if(children==null||children.size()==0){
                //如果孩子集合为空
                Node current = root;
                //children = new ArrayList<>();
                for(int i=0;i<chars.length;i++){
                    char c = chars[i];
                    Node ele = new Node(c,current);
                    children.add(ele);

                    current = ele;
                    children = new ArrayList<>();
                    ele.setChildren(children);
                }
            }else{
                //如果孩子集合不为空
                Node current = root;

                int breakIndex=-1;
                tag:
                for(int j=0;j<chars.length;j++){
                    char c = chars[j];

                    boolean exist = false;
                    for(Node child:children){
                        if(child.getValue()==c){
                            current = child;
                            children = child.getChildren();
                            exist = true;
                        }
                    }
                    //如果没有该节点,则需要跳出外部循环，且创建新的分支
                    if(!exist){
                        breakIndex = j;
                        break tag;
                    }
                }

                if(breakIndex>-1){
                    //如果断点存在，说明需要添加新节点
                    for(int k=breakIndex;k<chars.length;k++){
                        children = current.getChildren();
                        char c = chars[k];
                        Node tire = new Node(c,current);
                        children.add(tire);

                        current = tire;
                        tire.setChildren(new ArrayList<>());
                    }
                }
            }
            return true;
        }


        boolean search(String string){
            char[] target = string.toCharArray();
            Node current = root;

            for(int i=0;i<target.length;i++){
                char c = target[i];
                boolean exist = false;

                List<Node> children = current.getChildren();
                for(Node tire:children){
                    if(tire.getValue()==c){
                        current = tire;
                        exist=true;
                        break;
                    }
                }
                if(!exist){
                    return false;
                }
            }
            return true;
        }

        List<String> startWith(String prefix){
            char[] target = prefix.toCharArray();
            Node current = root;

            for(int i=0;i<target.length;i++){
                char c = target[i];
                boolean exist = false;

                List<Node> children = current.getChildren();
                for(Node tire:children){
                    if(tire.getValue()==c){
                        current = tire;
                        exist=true;
                        break;
                    }
                }
                if(!exist){
                    return null;
                }
            }

            Node parent = current.getParent();

            List<String> ans = new ArrayList<>();
            print(ans,parent);
            List result = new ArrayList();
            prefix = prefix.substring(0,prefix.length()-1);
            for(String an:ans){
                String s = prefix + an;
                result.add(s);
            }
            return result;
        }

        void print(List<String> result, Node node){
            List<Node> children = node.getChildren();

            if(result.size()>0){
                String end = result.get(result.size()-1);
                end = end.equals("-")?String.valueOf(node.getValue()):end+String.valueOf(node.getValue());
                result.remove(result.size()-1);
                result.add(end);
            }else{
                result.add(String.valueOf(node.getValue()));
            }



            if(children!=null&&children.size()>0){
                for(Node child:children){
                    print(result,child);
                    if(child.getChildren()==null||child.getChildren().size()==0){
                        result.add("-");
                    }
                }
            }
        }
    }
    static class Node {
        char value;
        List<Node> children;
        Node parent;

        Node(char c, Node parent){
            this.value = c;
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        public char getValue() {
            return value;
        }

        public void setValue(char value) {
            this.value = value;
        }
    }

}
