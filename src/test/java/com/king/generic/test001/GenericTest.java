package com.king.generic.test001;

import org.junit.Test;

public class GenericTest {

    @Test
    public void TestGetKey01(){
        Generic<String> a = new Generic<>("shengll");

        assert a.getKey()=="shengll" ;
    }
    @Test
    public void TestGetKey02(){
        Generic a = new Generic("shengll");
        assert a.getKey()=="shengll" ;

        /*
        * 定义的泛型类，就一定要传入泛型类型实参么？并不是这样，在使用泛型的时候如果传入泛型实参，则会根据传入的泛型实参做相应的限制，
        * 此时泛型才会起到本应起到的限制作用。如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
        * */
        Generic i = new Generic(8888);
        System.out.println(i.getKey());
    }

    @Test
    public void TestShowKeyValue(){

        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

            //编译报错
        //System.out.println(gNumber.showKeyValue1(gInteger));
    }
    @Test
    public void TestShowKeyValue2(){

        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

        gNumber.showKeyValue2(gInteger);
    }


    @Test
    public void TestShowKeyName(){

        Generic<Number> gNumber = new Generic<Number>(456956);

        gNumber.showKeyName(gNumber);
    }

}
