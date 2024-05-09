package com.example.clickyhero.student_demo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickyhero.R;

import java.util.ArrayList;

public class StudentAdapter2 extends RecyclerView.Adapter<StudentAdapter2.StudentViewHolder> {
    private final ArrayList<Student2> alStudents;
    private final Context context;

    public StudentAdapter2(ArrayList<Student2> alStudents, Context context) {
        this.alStudents = alStudents;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_testing, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student2 student = alStudents.get(position);
        holder.tvStudentName.setText(student.getFirstName());

        // Generate arrows based on button press combination
        ButtonPress buttonPress = ButtonPresses.COMBINATIONS[position % ButtonPresses.COMBINATIONS.length];
        int[] presses = buttonPress.getPresses();
        StringBuilder arrows = new StringBuilder();
        for (int press : presses) {
            Drawable arrowDrawable = getArrowDrawableForPress(press);
            if (arrowDrawable != null) {
                arrows.append(getArrowTextForPress(press));
            }
        }
        holder.tvArrows.setText(arrows.toString());
    }

    @Override
    public int getItemCount() {
        return alStudents.size();
    }

    private Drawable getArrowDrawableForPress(int press) {
        switch (press) {
            case 1:
                return ContextCompat.getDrawable(context, R.drawable.up);
            case 2:
                return ContextCompat.getDrawable(context, R.drawable.down);
            case 3:
                return ContextCompat.getDrawable(context, R.drawable.left);
            case 4:
                return ContextCompat.getDrawable(context, R.drawable.right);
            default:
                return null;
        }
    }

    private String getArrowTextForPress(int press) {
        switch (press) {
            case 1:
                return "↑";
            case 2:
                return "↓";
            case 3:
                return "←";
            case 4:
                return "→";
            default:
                return "";
        }
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        TextView tvGender;
        TextView tvArrows;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvArrows = itemView.findViewById(R.id.tvArrows);
        }
    }
}