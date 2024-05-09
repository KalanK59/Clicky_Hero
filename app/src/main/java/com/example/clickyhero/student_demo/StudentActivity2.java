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

        setContentView(R.layout.activity_clickyhero_main);

        rvStudent = findViewById(R.id.rvStudent);

        LinearLayoutManager layoutManager =  new LinearLayoutManager(StudentActivity2.this);
        rvStudent.setLayoutManager(layoutManager);

        alStudents = new ArrayList<>();
        alStudents.add(new Student2( "Reinforce"));
        alStudents.add(new Student2( "Resupply"));
        alStudents.add(new Student2("Eagle Rearm"));
        alStudents.add(new Student2("Eagle Airstrike"));
        alStudents.add(new Student2("Eagle 500kg Bomb"));

        studentAdapter = new StudentAdapter2(alStudents, StudentActivity2.this);
        rvStudent.setAdapter(studentAdapter);

        // Add divider
        setRecyclerViewDivider();
    }

    private void setRecyclerViewDivider() {

    }
}