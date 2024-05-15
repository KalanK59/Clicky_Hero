package com.example.clickyhero.student_demo;

import java.util.ArrayList;

public class Student2Backup {

    private String comboID;
    private String name;
    private int[] combos;

    public Student2Backup(String name, int[] combos) {
        this.combos = combos;
        this.name = name;
    }

    public Student2Backup() {

    }

    public String getComboID() {
        return comboID;
    }

    public void setComboID(String comboID) {
        this.comboID = comboID;
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