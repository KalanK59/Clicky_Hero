package com.example.clickyhero.student_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class
ComboMainActivity extends AppCompatActivity {
    RecyclerView rvStudent;
    static ArrayList<Combos> alStudents;
    private ComboAdapter studentAdapter;
    private Button btnRestart;

    private TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_clickyhero_main);

        rvStudent = findViewById(R.id.rvStudent);
        btnRestart = findViewById(R.id.btnRestart);

        tvScore = findViewById(R.id.tvScore);

        LinearLayoutManager layoutManager =  new LinearLayoutManager(ComboMainActivity.this);
        rvStudent.setLayoutManager(layoutManager);

        DBHelper dbComboHelper = new DBHelper(this);
        alStudents = dbComboHelper.getAllCombos();
        if (alStudents.isEmpty()) {
            alStudents = new ArrayList<>();
            alStudents.add(new Combos(0, "Reinforce", new int[]{R.drawable.up, R.drawable.down, R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alStudents.add(new Combos(1, "Resupply", new int[]{R.drawable.down, R.drawable.down, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alStudents.add(new Combos(2,"Eagle Rearm", new int[]{R.drawable.up, R.drawable.up, R.drawable.left, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alStudents.add(new Combos(3,"Eagle Airstrike", new int[]{R.drawable.up, R.drawable.right, R.drawable.down, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alStudents.add(new Combos(4,"Eagle 500kg Bomb", new int[]{R.drawable.up, R.drawable.left, R.drawable.down, R.drawable.down, R.drawable.down, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            dbComboHelper.addCombos(alStudents);
            alStudents = dbComboHelper.getAllCombos();
        }

        studentAdapter = new ComboAdapter(alStudents, ComboMainActivity.this);

        studentAdapter.setOnStudentClickListener((position, student) -> {
            Log.d("Student", student.toString());

            Intent colorIntent =  new Intent(ComboMainActivity.this, CombinationActivity.class);

            colorIntent.putExtra("student", student);
            int[] imageResources = student.getCombos();
            colorIntent.putExtra("imageResource", imageResources);
            colorIntent.putExtra("name", student.getName());
            startActivity(colorIntent);
            finish();

        });
        rvStudent.setAdapter(studentAdapter);
        btnRestart.setOnClickListener(v -> restartGame());
}
    // Changes the tvScore text with the correct value.
    @Override
    protected void onResume() {
        super.onResume();
        studentAdapter.notifyDataSetChanged();

        //get the update from the dbHelper show the updated colours for the results
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int score = prefs.getInt("score", 0);
        tvScore.setText(String.valueOf(score));
    }

    private void restartGame() {
        DBHelper dbComboHelper = new DBHelper(this);
        dbComboHelper.removeCombos();

        alStudents = dbComboHelper.getAllCombos();
        if (alStudents.isEmpty()) {
            alStudents = new ArrayList<>();
            alStudents.add(new Combos(0, "Reinforce", new int[]{R.drawable.up, R.drawable.down, R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alStudents.add(new Combos(1, "Resupply", new int[]{R.drawable.down, R.drawable.down, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alStudents.add(new Combos(2,"Eagle Rearm", new int[]{R.drawable.up, R.drawable.up, R.drawable.left, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alStudents.add(new Combos(3,"Eagle Airstrike", new int[]{R.drawable.up, R.drawable.right, R.drawable.down, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alStudents.add(new Combos(4,"Eagle 500kg Bomb", new int[]{R.drawable.up, R.drawable.left, R.drawable.down, R.drawable.down, R.drawable.down, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            dbComboHelper.addCombos(alStudents);
            alStudents = dbComboHelper.getAllCombos();
        }
        studentAdapter = new ComboAdapter(alStudents, ComboMainActivity.this);
        rvStudent.setAdapter(studentAdapter);

        // Set score and correct combo count to 0
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putInt("score", 0);
        editor.putInt("correctCombos", 0);
        editor.apply();

        // Update tvScore text
        tvScore.setText("0");
    }

}