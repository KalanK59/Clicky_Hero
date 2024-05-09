package com.example.clickyhero.student_demo;

import com.example.clickyhero.R;

public class ButtonPresses {
    public static final ButtonPress[] COMBINATIONS = {
            new ButtonPress("Down", new int[]{1, 2, 3, 4}, R.drawable.down),
            new ButtonPress("Left", new int[]{5, 6, 7, 8}, R.drawable.left),
            new ButtonPress("Right", new int[]{9, 10, 11, 12}, R.drawable.right),
            new ButtonPress("Up", new int[]{13, 14, 15, 16}, R.drawable.up)
    };
}