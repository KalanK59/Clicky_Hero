package com.example.clickyhero.combos;

import java.io.Serializable;

public class Combos implements Serializable {
    // Combo ID
    private int comboID;
    // Combo name
    private String name;
    // Array of combo steps
    private int[] combos;
    // Status of the combo (0 = default, 1 = correct, -1 = incorrect)
    private int correct;

    // Constructor with parameters
    public Combos(int comboID, String name, int[] combos) {
        this.comboID = comboID;
        this.name = name;
        this.combos = combos;
        this.correct = 0; // Default status
    }

    // Default constructor
    public Combos() {

    }

    // Getter for comboID
    public int getComboID() {
        return comboID;
    }

    // Setter for comboID
    public void setComboID(int comboID) {
        this.comboID = comboID;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for combos
    public int[] getCombos() {
        return combos;
    }

    // Setter for combos
    public void setCombos(int[] combos) {
        this.combos = combos;
    }

    // Getter for correct status
    public int getCorrect() {
        return correct;
    }

    // Setter for correct status
    public void setCorrect(int correct) {
        this.correct = correct;
    }
}