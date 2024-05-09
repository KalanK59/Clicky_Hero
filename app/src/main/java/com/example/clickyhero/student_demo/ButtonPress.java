package com.example.clickyhero.student_demo;

public class ButtonPress {
    private String name;
    private int[] presses;
    private int imageResId;

    public ButtonPress(String name, int[] presses, int imageResId) {
        this.name = name;
        this.presses = presses;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int[] getPresses() {
        return presses;
    }

    public int getImageResId() {
        return imageResId;
    }
}