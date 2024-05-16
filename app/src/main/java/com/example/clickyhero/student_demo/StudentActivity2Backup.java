package com.example.clickyhero.student_demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickyhero.R;

import java.util.ArrayList;
import java.util.Collections;

public class StudentActivity2Backup extends AppCompatActivity {

    public static int score;
    RecyclerView rvStudent;
    static ArrayList<Student2Backup> alStudents;
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
        alStudents.add(new Student2Backup(0, "Reinforce", new int[]{R.drawable.up, R.drawable.down, R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2Backup(1, "Resupply", new int[]{R.drawable.down, R.drawable.down, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2Backup(2,"Eagle Rearm", new int[]{R.drawable.up, R.drawable.up, R.drawable.left, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2Backup(3,"Eagle Airstrike", new int[]{R.drawable.up, R.drawable.right, R.drawable.down, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
        alStudents.add(new Student2Backup(4,"Eagle 500kg Bomb", new int[]{R.drawable.up, R.drawable.left, R.drawable.down, R.drawable.down, R.drawable.down, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));

        DBComboHelper dbComboHelper = new DBComboHelper(this);
        dbComboHelper.addCombos(alStudents);


        studentAdapter = new StudentAdapter2Backup(alStudents, StudentActivity2Backup.this);
        rvStudent.setAdapter(studentAdapter);


        btnRestart.setOnClickListener(v -> restartGame());

}
    // Changes the tvScore text with the correct value.
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int score = prefs.getInt("score", 0);
        tvScore.setText(String.valueOf(score));

//        Intent intent = new Intent(StudentActivity2Backup.this,  CongratulationsActivity.class);
//        intent.putExtra("score", score);
//        startActivity(intent);

        //rvStudent.setBackgroundColor(Color.GREEN);
        //rvStudent.setBackgroundColor(Color.RED);

    }

    private void restartGame() {
        // Set score to 0
        getSharedPreferences("MyPrefs", MODE_PRIVATE).edit().putInt("score", 0).apply();

        // Update tvScore text
        tvScore.setText("0");

        // Randomize order of combinations
        Collections.shuffle(alStudents);

        // Notify the adapter that the data has changed
        studentAdapter.notifyDataSetChanged();
    }
}