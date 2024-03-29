package com.king.test.jdk17.ortools;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 假设有10个二手车车辆评估师，此时接到10个车辆评估单，请按照距离综合最短的方式派单。
 *
 * 1,贪心算法 总是优先距离最短的匹配，越是往后，距离越大。
 *
 * 2,按照距离中心点较远的优先选择。
 *
 * 测试结果：总是第二种方法，得出的组合的总距离较近。
 *
 */

public class MyDemo {

    public static void main(String[] args) {
        int[][] vehicleLocation = new int[][]{{80,35},{58,43},{17,29},{7,19},{170,409},{157,349},{187,849},{617,849}};
        int[][] appraiserLocation = new int[][]{{108,15},{75,154},{19,11},{29,21},{207,59},{137,429},{617,749},{517,249}};

        findMinDistance(vehicleLocation,appraiserLocation);

    }

    private static List<Double> findMinDistance(int[][] vehicleLocation, int[][] appraiserLocation) {

        System.out.println(vehicleLocation[1][0]);
        System.out.println(vehicleLocation[1][1]);
        List<List<Double>> cost = new ArrayList<>();
        for(int i=0;i<appraiserLocation.length;i++){
            int x = appraiserLocation[i][0];
            int y = appraiserLocation[i][1];

            List<Double> appraiserCost = new ArrayList<>();
            for(int j=0;j<vehicleLocation.length;j++){
                int vX = vehicleLocation[j][0];
                int vY = vehicleLocation[j][1];

                Double distance =Math.sqrt(Math.pow(x-vX,2)+Math.pow(y-vY,2));
                System.out.println("x:"+x+",vX:"+vX+",y:"+y+",vY:"+vY+",distance:"+distance);
                appraiserCost.add(distance);
            }
            cost.add(appraiserCost);
        }

        System.out.println(cost);
        Boolean[][] valid = new Boolean[appraiserLocation.length][vehicleLocation.length];
        for(int m=0;m<appraiserLocation.length;m++){
            for(int n=0;n<vehicleLocation.length;n++){
                valid[m][n] = Boolean.TRUE;
            }
        }

        List<Integer[]> result = new ArrayList<>();
        //第一种方法
        findMinDistanceCombination(cost,valid,result);

        Double sum = 0.0d;
        for(Integer[] r:result){
            System.out.println(r[0]+"选择地点："+r[1]+"，花费距离为："+cost.get(r[0]).get(r[1]));
            sum+=cost.get(r[0]).get(r[1]);
        }

        System.out.println("**********总是距离优先****************总花费："+sum);
        Boolean[][] ban = new Boolean[appraiserLocation.length][vehicleLocation.length];
        for(int m=0;m<appraiserLocation.length;m++){
            for(int n=0;n<vehicleLocation.length;n++){
                ban[m][n] = Boolean.TRUE;
            }
        }
        List<Integer[]> result2 = new ArrayList<>();
        //第二种方法
        findMinDistanceByCenterPoint(cost,ban,result2);
        Double sum2 = 0.0D;
        for(Integer[] r:result2){
            System.out.println(r[0]+"选择地点："+r[1]+"，花费距离为："+cost.get(r[0]).get(r[1]));
            sum2+=cost.get(r[0]).get(r[1]);
        }
        System.out.println("中心点距离优先总花费："+sum2);

        Boolean[][] ban2 = new Boolean[appraiserLocation.length][vehicleLocation.length];
        for(int m=0;m<appraiserLocation.length;m++){
            for(int n=0;n<vehicleLocation.length;n++){
                ban2[m][n] = Boolean.TRUE;
            }
        }
        List<Integer[]> result3 = new ArrayList<>();
        violence(cost,ban2,result3);
        return null;
    }

    //暴力求解
    private static void violence(List<List<Double>> cost, Boolean[][] ban2, List<Integer[]> result3) {
        TreeNode root =  buildTree(cost);
        //print(root);

        List<TreeNode> leafList = new ArrayList<>();
        findLeaf(root,leafList);


        System.out.println("leaf:"+leafList.size());
        Double min = Double.MAX_VALUE;
        for(TreeNode leaf:leafList){
            TreeNode node =  leaf;
            //System.out.print("level:"+node.getLevel()+"|index:"+node.index+"|val:"+node.getVal());
            Double sum = cost.get(node.level).get(node.index);

            while (node.parent!=null&&node.parent.getLevel()>-1){
                //System.out.print("|level:"+node.parent.getLevel()+"|index:"+node.parent.getIndex()+"|val:"+node.parent.getVal());
                Integer level  = node.parent.getLevel();
                Integer index = node.parent.index;
                if(index>-1){
                    sum+=cost.get(level).get(index);
                }
                node = node.parent;

            }
            //System.out.print("sum:"+sum+"||");
            if(sum<min){
                min = sum;
            }
        }
        System.out.println("\r");
        System.out.println("min:"+min);
    }

    static void print(TreeNode root){
        if(root==null){
            return;
        }
        List<TreeNode> children = root.getChildren();
        System.out.println(root.getIndex()+"::");
        for(TreeNode node:children){
            print(node);
        }
    }
//[[34.40930106817051, 57.30619512757761, 92.07062506576133, 101.07917688624102],
// [119.10499569707393, 112.29425630903836, 137.80058055030102, 151.15885683611134],
// [65.55150646628954, 50.44799302251776, 18.110770276274835, 14.422205101855956],
// [52.88667128870941, 36.40054944640259, 14.422205101855956, 22.090722034374522]]
    static void findLeaf(TreeNode root,List<TreeNode> leafList){
        if(root==null){
            return;
        }
        if(root.getChildren()==null||root.getChildren().size()==0){
            leafList.add(root);
            return;
        }
        List<TreeNode> children = root.getChildren();
        for(TreeNode child:children){
            findLeaf(child,leafList);
        }
    }
    private static TreeNode buildTree(List<List<Double>> cost) {

        if(cost==null){
            return null;
        }
        TreeNode root = new TreeNode(-1,-1,0.0D);

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        for(int i=0;i<cost.size();i++){
            tag:
            while(!deque.isEmpty()){
                TreeNode parent = deque.poll();

                if(parent==null){
                    continue;
                }
                Integer parentLevel = parent.getLevel();
                List<TreeNode> children = new ArrayList<>();
                List<Double> list = cost.get(i);
                for(int j=0;j<list.size();j++){
                    if(checkAncestors(parent,j)){
                        Integer level = parentLevel+1;
                        TreeNode node = new TreeNode(j,level,list.get(j));
                        node.setParent(parent);
                        children.add(node);
                        //添加到队列
                        //TreeNode pre = deque.peekLast();
                        if(i!=level){
                            //如果 前置元素和新添加的元素的层级不一样，那么添加截止节点
                            deque.offer(parent);
                            break tag;
                        }else{
                            deque.offer(node);
                        }
                    }
                }
                parent.setChildren(children);
            }
        }

        return root;
    }

    //校验祖先节点不包含当前节点的下标 true表示不包含
    private static boolean checkAncestors(TreeNode node,Integer index) {
        if(node.parent==null){
            return true;
        }
        if(node.getIndex()==index)return false;

        return checkAncestors(node.parent,index);
    }

    private static void findMinDistanceByCenterPoint(List<List<Double>> cost, Boolean[][] ban, List<Integer[]> result) {
        HashMap<Double,Integer> centerPoint = new HashMap<>();
        List<Double> sortList = new ArrayList<>();
        for(int i=0;i<cost.size();i++){
            List<Double> dis = cost.get(i);
            Double all = 0.0D;
            for(Double d:dis){
                all+=d;
            }
            centerPoint.put(all,i);
            sortList.add(all);
        }
        //System.out.println(sortList);
        sortList = sortList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        System.out.println(sortList);
        System.out.println(centerPoint);

        for(int i=0;i<sortList.size();i++){
            Double key = sortList.get(i);
            Integer index = centerPoint.get(key);
            //System.out.println("选择顺序："+index);
            List<Double> coll = cost.get(index);
            Double min = Double.MAX_VALUE;
            Integer col = -1;
            for(int j=0;j<coll.size();j++){
                if(ban[index][j]){
                    if(coll.get(j)<min){
                        min  = coll.get(j);
                        col  = j;
                    }
                }
            }
            //System.out.println(index+"-"+col);
            if(min!=Double.MAX_VALUE&&col!=-1){
                result.add(new Integer[]{index,col});
                //System.out.println(index+"::"+col+"::"+cost.get(index).get(col));
                //添加禁止选项
                Boolean[] appor = ban[index];
                for(int k=0;k<appor.length;k++){
                    ban[index][k] = Boolean.FALSE;
                }
                for(int y=0;y<ban.length;y++){
                    ban[y][col] = Boolean.FALSE;
                }
            }


            //System.out.println("ban: --------------");
            /*for(Boolean[] a:ban){
                for(Boolean b:a){
                    System.out.print(b+",");
                }
                System.out.println("\r");
            }*/
            //System.out.println("ban: ------------");

        }
    }


    static List<Integer[]> findMinDistanceCombination(List<List<Double>> cost,Boolean[][] valid,List<Integer[]> result){

        // 判断终止条件
        Boolean end = Boolean.FALSE;
        jump:
        for(int p=0; p<valid.length;p++){
            Boolean[] b = valid[p];
            for(int q=0;q<b.length;q++){
                if(b[q]==true){
                    end = Boolean.TRUE;
                    break jump;
                }
            }
        }

        if(!end){
            return result;
        }
        //System.out.println("------------------------------");
        HashMap<Integer,Integer> chosen = new HashMap<>();


        for(int i=0;i<cost.size();i++){
            if(!checkValid(valid[i])){
                continue;
            }
            List<Double> aCost = cost.get(i);
            Double acMin = Double.MAX_VALUE;
            int vLocation = -1;
            for(int j=0;j<aCost.size();j++){
                if(!valid[i][j]){
                    continue;
                }
                Double distance = aCost.get(j);
                if(distance<acMin){
                    acMin = distance;
                    vLocation = j;
                }
            }
            chosen.put(i,vLocation);
            System.out.println(i+" chose "+vLocation+",minDis:"+acMin);
        }
        System.out.println("粗选："+chosen);
        HashMap<Integer,Integer> repeatTimes = new HashMap<>();
        //找到冲突的选择组合
        for(Map.Entry<Integer,Integer> chose:chosen.entrySet()){
            int key=chose.getKey();
            int value=chose.getValue();
            if(repeatTimes.containsKey(value)){
                repeatTimes.put(value,repeatTimes.get(value)+1);
            }else{
                repeatTimes.put(value,1);
            }
        }
        System.out.println("重复选择："+repeatTimes);

        HashSet<Integer> repeat = new HashSet<>();
        for(Map.Entry<Integer,Integer> e:repeatTimes.entrySet()){
            if(e.getValue()>1){
               repeat.add(e.getKey());
            }
        }
        System.out.println("重复选择："+repeat);

        //需要解决冲突，需选择冲突发生时距离最小的那个 组合
        HashMap<Integer,Integer> refactorChosenMap = new HashMap();
        HashMap<Integer,Integer> conflictChosenMap  = new HashMap<>();
        List<Integer[]> conflictCombination =  new ArrayList<>();

        //筛选冲突的选择组合
        for(Integer v:repeat){
            for(Map.Entry<Integer,Integer> chose:chosen.entrySet()){
                int key=chose.getKey();
                int value=chose.getValue();
                if(value==v){
                    conflictChosenMap.put(key,value);
                }
            }
        }
        System.out.println("冲突："+conflictChosenMap);
        //删除冲突的组合
        for(Map.Entry<Integer,Integer> entry:conflictChosenMap.entrySet()){
            chosen.remove(entry.getKey());
        }
        System.out.println("添加处理冲突后的组合："+chosen);

        //挑选冲突组合中最小的距离的组合 放入选择结果中
        for(Integer v:repeat){
            Double distance = Double.MAX_VALUE;
            Integer app = -1;
            Integer vec = -1;
            for(Map.Entry<Integer,Integer> entry:conflictChosenMap.entrySet()){
                Integer key = entry.getKey();
                Integer val = entry.getValue();
                Double cos = cost.get(key).get(val);
                if(v==val){
                    if(distance==Double.MAX_VALUE||cos<distance){
                        distance = cos;
                        app  = key;
                        vec  = val;
                    }
                }
            }
            if(app!=-1&&vec!=-1&&distance!=Double.MAX_VALUE){
                System.out.println("添加冲突处理元素组合：key:"+app+",value:"+vec+",distance:"+distance);
                chosen.put(app,vec);
            }
        }

        System.out.println("添加冲突处理后的选择组合："+chosen);
        for(Integer[] r:result){
            if(r[0]!=null){
                System.out.println(r[0]+" chose "+r[1]);
            }
        }
        int index = 0;
        for(Map.Entry<Integer,Integer> chose:chosen.entrySet()){
            Integer appraiser = chose.getKey();
            Integer vehicleLocation = chose.getValue();
            /*result[index][0] = appraiser;
            result[index][1] = vehicleLocation;*/
            result.add(new Integer[]{appraiser,vehicleLocation});
            index++;
            // 把已经作出选择的评估师和车辆地址置为不可用。

            for(int k=0;k<valid.length;k++){

                Boolean[] line = valid[k];
                for(int l=0;l<line.length;l++){
                    if(k==appraiser){
                        valid[k][l] = Boolean.FALSE;
                    }else{
                        if(l==vehicleLocation){
                            valid[k][l] = Boolean.FALSE;
                        }
                    }
                }
            }
        }

        /*System.out.println("valid: --------------");
        for(Boolean[] a:valid){
            for(Boolean b:a){
                System.out.print(b+",");
            }
            System.out.println("\r");
        }
        System.out.println("valid: ------------");
        for(Integer[] r:result){
            if(r[0]!=null){
                System.out.println(r[0]+" chose "+r[1]);
            }
        }*/
        // 把已经作出选择的评估师和车辆地址置为不可用。

        return findMinDistanceCombination(cost,valid,result);
    }


    static boolean checkValid(Boolean[] valid){
        for(int  i=0;i< valid.length;i++){
            if(valid[i]==Boolean.TRUE){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    static class TreeNode {
        TreeNode parent;
        List<TreeNode> children;

        Double  val;
        Integer level;
        Integer index;

        public Double getVal() {
            return val;
        }

        public void setVal(Double val) {
            this.val = val;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }


        TreeNode(Integer index,Integer level){
            this.index = index;
            this.level = level;
        }

        TreeNode(Integer index,Integer level,Double val){
            this.val = val;
            this.index = index;
            this.level = level;
        }
        public List<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode> children) {
            this.children = children;
        }
    }
}
