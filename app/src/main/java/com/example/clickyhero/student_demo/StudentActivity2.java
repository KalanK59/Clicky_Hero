package com.example.clickyhero.student_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickyhero.R;

import java.util.ArrayList;
import java.util.Collections;

public class StudentActivity2 extends AppCompatActivity {

    RecyclerView rvStudent;
    ArrayList<Student2> alStudents;
    private StudentAdapter2 studentAdapter;
    private Button btnRestart;

    private TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This is what hides the action bar.

        setContentView(R.layout.activity_clickyhero_main);

        rvStudent = findViewById(R.id.rvStudent);
        btnRestart = findViewById(R.id.btnRestart);

        tvScore = findViewById(R.id.tvScore);

        LinearLayoutManager layoutManager =  new LinearLayoutManager(StudentActivity2.this);
        rvStudent.setLayoutManager(layoutManager);

        alStudents = new ArrayList<>();
        alStudents.add(new Student2( "Reinforce", new int[]{R.drawable.up, R.drawable.down, R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2( "Resupply", new int[]{R.drawable.down, R.drawable.down, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2("Eagle Rearm", new int[]{R.drawable.up, R.drawable.up, R.drawable.left, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2("Eagle Airstrike", new int[]{R.drawable.up, R.drawable.right, R.drawable.down, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2("Eagle 500kg Bomb", new int[]{R.drawable.up, R.drawable.left, R.drawable.down, R.drawable.down, R.drawable.down, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));

        studentAdapter = new StudentAdapter2(alStudents, StudentActivity2.this);
        rvStudent.setAdapter(studentAdapter);


        btnRestart.setOnClickListener(v -> restartGame());
    }

    private void restartGame() {
        // Randomize order of combinations
        Collections.shuffle(alStudents);

        // Notify the adapter that the data has changed
        studentAdapter.notifyDataSetChanged();
    }


}