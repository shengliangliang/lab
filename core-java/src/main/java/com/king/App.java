package com.king;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();


        int a = 87;
        System.out.println(a - (a >> 3 << 3));
        System.out.println(a);
        /*while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            app.sayHello("rebot",18);
        }*/
    }

    public String sayHello(String name, int age) {
        System.out.println("I am alive");
        return "hello everyone";
    }

}
