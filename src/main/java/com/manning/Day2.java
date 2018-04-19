package com.manning;

/**
 * Created by hanyu on 2018/4/7.
 */
public enum Day2 {

    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");

    private String descs;

    Day2(String descs) {
        this.descs = descs;
    }

    public String getDescs() {
        return descs;
    }

    public void setDesc(String descs) {
        this.descs = descs;
    }



    public static void main(String[] args) {
        Day2[] values = Day2.values();
        for (Day2 day : values){
            System.out.println(day.name()+""+day.toString());
        }
    }

    @Override
    public String toString() {
        return descs;
    }
}
