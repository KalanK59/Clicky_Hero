package com.example.clickyhero;

public class Combination {
    private String name;
    private String sequence;
    private int imageResourceId;

    public Combination(String name, String sequence, int imageResourceId) {
        this.name = name;
        this.sequence = sequence;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getSequence() {
        return sequence;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}