package com.example.clickyhero.student_demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickyhero.R;

import java.util.ArrayList;
import java.util.Collections;

public class StudentAdapter2 extends RecyclerView.Adapter<StudentAdapter2.StudentViewHolder> {
    private final ArrayList<Student2> alStudents;

    public StudentAdapter2(ArrayList<Student2> alStudents) {
        this.alStudents = alStudents;
        // Shuffle the ArrayList
        Collections.shuffle(this.alStudents);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_clickyhero, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student2 student = alStudents.get(position);
        holder.Name.setText(student.getName());
        int[] combos = student.getCombos();
        for (int i = 0; i < Math.min(combos.length, 8); i++) {
            switch (i) {
                case 0:
                    holder.imgArrow1.setImageResource(combos[i]);
                    break;
                case 1:
                    holder.imgArrow2.setImageResource(combos[i]);
                    break;
                case 2:
                    holder.imgArrow3.setImageResource(combos[i]);
                    break;
                case 3:
                    holder.imgArrow4.setImageResource(combos[i]);
                    break;
                case 4:
                    holder.imgArrow5.setImageResource(combos[i]);
                    break;
                case 5:
                    holder.imgArrow6.setImageResource(combos[i]);
                    break;
                case 6:
                    holder.imgArrow7.setImageResource(combos[i]);
                    break;
                case 7:
                    holder.imgArrow8.setImageResource(combos[i]);
                    break;
            }
        }

        // Set background color to turquoise
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.turquoise));

        holder.Container.setOnClickListener(v -> {
            int[] imageResources = student.getCombos();
            openCombinationsActivity(holder.itemView.getContext(), student.getName(), imageResources);
        });
    }

    public interface OnStudentClickListener {
        void onStudentClick(int position, Student2 student);
    }

    private OnStudentClickListener onStudentClickListener;

    public void setOnStudentClickListener(OnStudentClickListener onStudentClickListener) {
        this.onStudentClickListener = onStudentClickListener;
    }

    private void openCombinationsActivity(Context context, String name, int[] imageResource) {
        Intent intent = new Intent(context, CombinationActivity.class);
        intent.putExtra("imageResource", imageResource);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return alStudents.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView Name;

        ImageView imgArrow1;
        ImageView imgArrow2;
        ImageView imgArrow3;
        ImageView imgArrow4;
        ImageView imgArrow5;
        ImageView imgArrow6;
        ImageView imgArrow7;
        ImageView imgArrow8;

        LinearLayout Container;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tvName);
            Container = itemView.findViewById(R.id.Container);
            imgArrow1 = itemView.findViewById(R.id.imgArrow);
            imgArrow2 = itemView.findViewById(R.id.imgArrow1);
            imgArrow3 = itemView.findViewById(R.id.imgArrow2);
            imgArrow4 = itemView.findViewById(R.id.imgArrow3);
            imgArrow5 = itemView.findViewById(R.id.imgArrow4);
            imgArrow6 = itemView.findViewById(R.id.imgArrow5);
            imgArrow7 = itemView.findViewById(R.id.imgArrow6);
            imgArrow8 = itemView.findViewById(R.id.imgArrow7);
        }
    }
}