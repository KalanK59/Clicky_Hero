package com.example.clickyhero.student_demo;

import java.io.Serializable;

public class Combos implements Serializable {

    private int comboID;
    private String name;
    private int[] combos;

    private boolean correct;



    public Combos(int comboID, String name, int[] combos) {
        this.comboID = comboID;
        this.name = name;
        this.combos = combos;
        this.correct = true;
    }

    public Combos() {

    }

    public int getComboID() {
        return comboID;
    }

    public void setComboID(int comboID) {
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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

}