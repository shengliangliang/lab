package com.king.generic.test002;

import org.junit.Test;

public class TestElement {

    @Test
    public void testCamparable(){
        Element element1 = new Element(3,9);
        Element element2 = new Element(1,15);

        System.out.println(element1.compareTo(element2));
    }
}
