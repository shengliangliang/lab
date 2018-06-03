package com.king.io.util;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Statics {


    static boolean flag = false;

    private static void pattern(String line){
        String regexNodeBegin = "\\s*/\\*.*";
        String regiexNodeEnd = ".*\\*/\\s*";
        String regex = "//.*";
        String regexSpace = "\\s*";
        sum ++;

        if(line.matches(regexNodeBegin)&&line.matches(regiexNodeEnd)){
            anNum ++;
        }
        if (line.matches(regexNodeBegin)){
            anNum ++;
            flag =true;
        }else if(line.matches(regiexNodeEnd)){
            anNum ++;
            flag = false;
        }else if(line.matches(regexSpace)){
            nullNum++;
        }else if(line.matches(regex)){
            anNum ++;
        }else if(flag){
            anNum ++;
        }else {
            codeNum ++;
        }

    }




    private static void statics(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        String tempString = null;
        try {
            while ((tempString=reader.readLine())!=null){
                System.out.println(tempString);
                pattern(tempString);
            }
        }finally {
            reader.close();
        }



    }

    private static void aa(File file) throws FileNotFoundException {
        if(file.isDirectory()){
            File[] files = file.listFiles();

            System.out.println("parent dir:"+file.getPath());
            Arrays.asList(files).forEach(a-> System.out.println(a.getPath()));

            Arrays.asList(files).forEach(subfile->{
                try {
                    aa(subfile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }else{
            if(file.getPath().endsWith("java")){
                try {
                    statics(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    static int sum = 0;
    static int nullNum = 0;
    static int anNum = 0;
    static int codeNum = 0;

    public static void main(String[] args) throws FileNotFoundException {
        File root = new File("D:\\work\\autohome_project\\nettybook2");

        aa(root);

        System.out.println("sum:"+sum);
        System.out.println("nullNum:"+nullNum);
        System.out.println("anNum:"+anNum);
        System.out.println("codeNum:"+codeNum);

    }
}
