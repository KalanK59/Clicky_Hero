package com.example.clickyhero.student_demo;

public class Student2 {
    private String name;
    private int[] combos;

    public Student2(String name, int[] combos) {
        this.name = name;
        this.combos = combos;
    }

    public Student2() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getCombos() {
        return combos;
    }

    public void setCombos(int[] combos) {
        this.combos = combos;
    }
}