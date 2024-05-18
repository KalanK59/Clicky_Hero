package com.example.clickyhero.student_demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickyhero.R;

import java.util.ArrayList;
import java.util.Collections;

public class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.StudentViewHolder> {
    private final ArrayList<Combos> comboList;
    private final Context context;


    private boolean correct = false;
    private boolean incorrect = false;

    public ComboAdapter(ArrayList<Combos> alStudents, Context context) {
        this.comboList = alStudents;
        this.context = context;
        // Shuffle the ArrayList
        Collections.shuffle(this.comboList);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_clickyhero, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Combos student = comboList.get(position);
        holder.Name.setText(student.getName());
        holder.imgArrow1.setImageResource(student.getCombos()[0]);
        holder.imgArrow2.setImageResource(student.getCombos()[1]);
        holder.imgArrow3.setImageResource(student.getCombos()[2]);
        holder.imgArrow4.setImageResource(student.getCombos()[3]);
        holder.imgArrow5.setImageResource(student.getCombos()[4]);
        holder.imgArrow6.setImageResource(student.getCombos()[5]);
        holder.imgArrow7.setImageResource(student.getCombos()[6]);
        holder.imgArrow8.setImageResource(student.getCombos()[7]);

        holder.itemView.setBackgroundColor(Color.CYAN);

        switch (student.getCorrect()) {
            case 0: holder.itemView.setBackgroundColor(Color.CYAN);
            break;
            case 1: holder.itemView.setBackgroundColor(Color.GREEN);
            break;
            case -1: holder.itemView.setBackgroundColor(Color.RED);
            break;

        }

//        holder.Container.setOnClickListener(v -> {
//            int[] imageResources = student.getCombos();
//            openCombinationsActivity(student, student.getName(), imageResources);
//        });
        holder.itemView.setOnClickListener(view -> {
            onStudentClickListener.onStudentClick(position, comboList.get(position));
        });
    }

    private void openCombinationsActivity(Combos student, String name, int[] imageResource) {
        Intent intent = new Intent(context, CombinationActivity.class);
        intent.putExtra("imageResource", imageResource);
        intent.putExtra("name", name);
        intent.putExtra("student", student);
        context.startActivity(intent);
//        context.finish();

    }


    @Override
    public int getItemCount() {
        return comboList.size();
    }
    public interface OnStudentClickListener {
        void onStudentClick(int position, Combos combo);
    }
    private OnStudentClickListener onStudentClickListener;
    public void setOnStudentClickListener(OnStudentClickListener onStudentClickListener) {
        this.onStudentClickListener = onStudentClickListener;
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