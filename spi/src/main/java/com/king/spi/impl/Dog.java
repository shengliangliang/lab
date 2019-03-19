package com.king.spi.impl;

import com.king.spi.api.Animal;

public class Dog implements Animal {
    @Override
    public void run() {
        System.out.println("I am a dog !");
    }
}
