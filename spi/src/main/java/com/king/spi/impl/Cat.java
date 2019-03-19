package com.king.spi.impl;

import com.king.spi.api.Animal;

public class Cat implements Animal {
    @Override
    public void run() {
        System.out.println("I am a cat !");
    }
}
