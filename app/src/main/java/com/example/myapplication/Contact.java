package com.example.myapplication;

public class Contact {
    private String name;
    private int icon1;
    private int icon2;
    private int icon3;

    // Constructor
    public Contact(String name, int icon1, int icon2, int icon3) {
        this.name = name;
        this.icon1 = icon1;
        this.icon2 = icon2;
        this.icon3 = icon3;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getIcon1() {
        return icon1;
    }

    public int getIcon2() {
        return icon2;
    }

    public int getIcon3() {
        return icon3;
    }
}
