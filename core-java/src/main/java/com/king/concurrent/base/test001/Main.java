package com.king.concurrent.base.test001;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        Thread threads[]=new Thread[10];
        Thread.State status[]=new Thread.State[10];

        for(int i=0; i<10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if(i%2==0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else{
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }

            threads[i].setName("Thread "+i);
        }


        File f = new File(".\\src\\main\\resources\\log.txt");
        FileWriter file = null;
        try {
            URL path = Main.class.getResource("");
            System.out.println(path.getPath());
            System.out.println(f.getPath());
            file = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(file);
        for (int i=0; i<10; i++) {
            pw.println("Main : Status of Thread "+i+" : "+threads[i].getState());
            status[i]=threads[i].getState();
        }

        for (int i=0; i<10; i++) {
            threads[i].start();
        }

        boolean finish=false;
        while (!finish) {
            for (int i=0; i<10; i++){
                if (threads[i].getState()!=status[i]) {
                    writeThreadInfo(pw, threads[i],status[i]);
                    status[i]=threads[i].getState();
                }
            }
        }

    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {

        pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());

        pw.printf("Main : Priority: %d\n",thread.getPriority());

        pw.printf("Main : Old State: %s\n",state);

        pw.printf("Main : New State: %s\n",thread.getState());

        pw.printf("Main : ************************************\n");

        pw.flush();
    }

}
