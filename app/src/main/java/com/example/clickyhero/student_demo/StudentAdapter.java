package com.example.clickyhero.student_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.clickyhero.R;

import java.util.ArrayList;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private final ArrayList<Student> alStudents;

    public StudentAdapter(ArrayList<Student> alStudents) {
        this.alStudents = alStudents;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_student, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        holder.tvStudentID.setText(alStudents.get(position).getStudentID());
        holder.tvStudentName.setText(String.format("%s %s", alStudents.get(position).getFirstName(), alStudents.get(position).getLastName()));

    }

    @Override
    public int getItemCount() {
        return alStudents.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentID;
        TextView tvStudentName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentID = itemView.findViewById(R.id.tvStudentID);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
        }
    }
}
