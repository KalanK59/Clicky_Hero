package com.example.clickyhero.student_demo;

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

public class StudentActivity2Backup extends AppCompatActivity {

    RecyclerView rvStudent;
    ArrayList<Student2Backup> alStudents;
    private StudentAdapter2Backup studentAdapter;
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

        LinearLayoutManager layoutManager =  new LinearLayoutManager(StudentActivity2Backup.this);
        rvStudent.setLayoutManager(layoutManager);

        alStudents = new ArrayList<>();
        alStudents.add(new Student2Backup( "Reinforce", new int[]{R.drawable.up, R.drawable.down, R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2Backup( "Resupply", new int[]{R.drawable.down, R.drawable.down, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2Backup("Eagle Rearm", new int[]{R.drawable.up, R.drawable.up, R.drawable.left, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2Backup("Eagle Airstrike", new int[]{R.drawable.up, R.drawable.right, R.drawable.down, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2Backup("Eagle 500kg Bomb", new int[]{R.drawable.up, R.drawable.left, R.drawable.down, R.drawable.down, R.drawable.down, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));

        studentAdapter = new StudentAdapter2Backup(alStudents, StudentActivity2Backup.this);
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