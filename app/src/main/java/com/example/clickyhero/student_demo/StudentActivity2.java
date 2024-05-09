package com.example.clickyhero.student_demo;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.clickyhero.R;

import java.util.ArrayList;

public class StudentActivity2 extends AppCompatActivity {

    RecyclerView rvStudent;
    ArrayList<Student2> alStudents;
    private StudentAdapter2 studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This is what hides the action bar.

        setContentView(R.layout.activity_student);

        rvStudent = findViewById(R.id.rvStudent);

        LinearLayoutManager layoutManager =  new LinearLayoutManager(StudentActivity2.this);
        rvStudent.setLayoutManager(layoutManager);

        alStudents = new ArrayList<Student2>();
        alStudents.add(new Student2( "Reinforce",  1));
        alStudents.add(new Student2( "Resupply", 1));
        alStudents.add(new Student2("Eagle Rearm",  0));
        alStudents.add(new Student2("Eagle Airstrike",  0));
        alStudents.add(new Student2("Eagle 500kg Bomb",  0));

        studentAdapter = new StudentAdapter2(alStudents, StudentActivity2.this);
        rvStudent.setAdapter(studentAdapter);
    }
}