package com.example.clickyhero.student_demo;

public class Student2 {


    private String Name;

    private int[] combos;

    public Student2(String Name, int[] combos) {
        this.Name = Name;
        this.combos = combos;
    }

    public int[] getCombos() {
        return combos;
    }

    public void setCombos(int[] combos) {
        this.combos = combos;
    }


    public String getName() {
        return Name;
    }

    public void setFirstName(String firstName) {
        this.Name = firstName;
    }


}
