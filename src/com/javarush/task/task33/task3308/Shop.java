package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement
public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    public Shop() {
        count = 1;
        secretData = new String[]{"1", "2"};
        goods = new Goods();
    }

    public static class Goods {
        public List<String> names = new ArrayList<>();


        public Goods() {
            names = Arrays.asList("S1", "S2");
        }
    }
}
