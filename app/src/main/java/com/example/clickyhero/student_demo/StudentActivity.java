package com.example.clickyhero.student_demo;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickyhero.R;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    RecyclerView rvStudent;
    ArrayList<Student> alStudents;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This is what hides the action bar.

        setContentView(R.layout.activity_student);

        rvStudent = findViewById(R.id.rvStudent);

        LinearLayoutManager layoutManager =  new LinearLayoutManager(StudentActivity.this);
        rvStudent.setLayoutManager(layoutManager);

        alStudents = new ArrayList<Student>();
        alStudents.add(new Student("S1", "Maxwell", "Christian", 1, "Regina"));
        alStudents.add(new Student("S2", "Alex", "Wang", 1, "Regina"));
        alStudents.add(new Student("S3", "Christa", "Wunsch", 0, "Regina"));

        studentAdapter = new StudentAdapter(alStudents);
        rvStudent.setAdapter(studentAdapter);
    }
}