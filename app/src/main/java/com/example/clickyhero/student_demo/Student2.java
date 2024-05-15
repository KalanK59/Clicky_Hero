package com.example.clickyhero.student_demo;

import java.util.ArrayList;

public class Student2 {

    private String comboID;
    private String name;
    private ArrayList<Integer> combos;

    public Student2(String comboID, String name, ArrayList<Integer> combos) {
        this.comboID = comboID;
        this.name = name;
        this.combos = combos;
    }

    public Student2() {

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

    public ArrayList<Integer> getCombos() {
        return combos;
    }

    public void setCombos(ArrayList<Integer> combos) {
        this.combos = combos;
    }
}