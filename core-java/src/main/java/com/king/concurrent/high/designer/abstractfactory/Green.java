package com.king.concurrent.high.designer.abstractfactory;

public class Green implements Color{
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
