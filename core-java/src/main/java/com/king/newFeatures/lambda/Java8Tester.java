package com.king.newfeatures.lambda;

public class Java8Tester {
    static int x = 90;
    private int k = 0;
    public static void main(String[] args) {
        Java8Tester java8Tester = new Java8Tester();
        MathOperation addition = (int a, int b) -> a + b;
        MathOperation subtraction = (a,b)-> a-b;
        MathOperation multiplication = (int a,int b)->{return a * b;};
        MathOperation division = (int a,int b)->a/b;


        System.out.println("10 + 5 = " + java8Tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + java8Tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + java8Tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + java8Tester.operate(10, 5, division));


        GreetingService greetingService1 = message -> System.out.println(message);

        int i = 0;
        GreetingService greetingService2 = (message)->{
            System.out.println(message);
            //i = 9;  这里修改i=9 会编译错误，因为这里不能修改域外的局部变量，域外指的就是本{}块外部，main方法内部称为域外
            x = 100;  //可以修改公共变量
        };

        greetingService1.sayMessage("shengliangliang");
        greetingService2.sayMessage("路还很长");
    }

    private void test(){
        GreetingService greetingService2 = (message)->{
            System.out.println(message);
            //i = 9;  这里修改i=9 会编译错误，因为这里不能修改域外的局部变量，域外指的就是本{}块外部，main方法内部称为域外
            x = 100;  //可以修改公共变量
            k=10;


            int h = 0;

        };
    }

    interface MathOperation{
        int operation(int a,int b);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }


    interface GreetingService {
        void sayMessage(String message);
        default void aaa(){
            System.out.println("hhh");
        }
    }
}

