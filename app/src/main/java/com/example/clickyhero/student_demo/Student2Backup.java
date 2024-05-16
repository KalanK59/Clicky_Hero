package com.example.clickyhero.student_demo;

public class Student2Backup {

    private int comboID;
    private String name;
    private int[] combos;

    private boolean correct;


    public Student2Backup(int comboID, String name, int[] combos) {
        this.combos = combos;
        this.name = name;
        this.comboID = comboID;
    }

    public Student2Backup() {

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