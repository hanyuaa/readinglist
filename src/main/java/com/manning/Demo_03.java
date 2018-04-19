package com.manning;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hanyu on 2018/4/11.
 */
public class Demo_03 {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red",2));
        apples.add(new Apple("yellow",1));
        apples.add(new Apple("green",3));

        /*Collections.sort(apples, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        apples.sort(Comparator.comparing(Apple::getWeight));


        String s = "123";
        System.out.print(apples);*/
    }

    public static void show(Apple apple){
        System.out.println("color:"+apple.getColor());
    }
}

class Apple{

    private String color;

    private int weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
