package com.king.test.jdk17;

import java.util.HashMap;
import java.util.Map;

public class TestOutOfMemory {

    public static void main(String[] args) {
        Map<String, Goods> map = new HashMap<>();
        int counter = 1;
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Goods goods = new Goods();
            String[] types = new String[counter];
            for (int i = 0; i < types.length; i++) {
                types[i] = "type" + i;
            }
            goods.setName("hresh" + counter);
            goods.setPrice(Double.valueOf(counter));
            goods.setTypes(types);
            map.put(goods.getName(), goods);
            if (counter % 100 == 0) {
                System.out.println("put" + counter);
            }
            counter++;
        }
    }


}
class Goods {

    private String name;
    private Double price;
    private String[] types;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}