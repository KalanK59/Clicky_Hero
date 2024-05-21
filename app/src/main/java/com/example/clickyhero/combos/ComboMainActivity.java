package com.example.clickyhero.combos;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class ComboMainActivity extends AppCompatActivity {
    // RecyclerView to display combos
    RecyclerView rvCombos;
    // List of combos
    ArrayList<Combos> alComboList;
    // Adapter for RecyclerView
    private ComboAdapter comboAdapter;
    // Button to restart the game
    private Button btnRestart;
    // TextView to display the score
    private TextView tvScore;

    // Flag to track if the list has been shuffled
    private boolean isListShuffled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title and set full-screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_clickyhero_main);

        // Initialize UI components
        rvCombos = findViewById(R.id.rvCombos);
        btnRestart = findViewById(R.id.btnRestart);
        tvScore = findViewById(R.id.tvScore);

        // Set up RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(ComboMainActivity.this);
        rvCombos.setLayoutManager(layoutManager);

        // Get combos from the database
        DBHelper dbComboHelper = new DBHelper(this);
        alComboList = dbComboHelper.getAllCombos();
        if (alComboList.isEmpty()) {
            // Add default combos if the list is empty
            alComboList = new ArrayList<>();
            alComboList.add(new Combos(0, "Reinforce", new int[]{R.drawable.up, R.drawable.down, R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alComboList.add(new Combos(1, "Resupply", new int[]{R.drawable.down, R.drawable.down, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alComboList.add(new Combos(2, "Eagle Rearm", new int[]{R.drawable.up, R.drawable.up, R.drawable.left, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alComboList.add(new Combos(3, "Eagle Airstrike", new int[]{R.drawable.up, R.drawable.right, R.drawable.down, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alComboList.add(new Combos(4, "Eagle 500kg Bomb", new int[]{R.drawable.up, R.drawable.left, R.drawable.down, R.drawable.down, R.drawable.down, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            dbComboHelper.addCombos(alComboList);
            alComboList = dbComboHelper.getAllCombos();
        }

        // Set up the adapter for RecyclerView
        comboAdapter = new ComboAdapter(alComboList, ComboMainActivity.this);
        rvCombos.setAdapter(comboAdapter);

        // Set item click listener for RecyclerView items
        comboAdapter.setOnStudentClickListener((position, student) -> {
            // Start CombinationActivity with selected combo details
            Intent colorIntent = new Intent(ComboMainActivity.this, CombinationActivity.class);
            colorIntent.putExtra("student", student);
            int[] imageResources = student.getCombos();
            colorIntent.putExtra("imageResource", imageResources);
            colorIntent.putExtra("name", student.getName());
            startActivity(colorIntent);
            finish();
        });

        // Set click listener for the restart button
        btnRestart.setOnClickListener(v -> restartGame());
    }

    @Override
    protected void onResume() {
        super.onResume();
        comboAdapter.notifyDataSetChanged();

        // Update the score from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int score = prefs.getInt("score", 0);
        tvScore.setText(String.valueOf(score));
    }

    private void restartGame() {
        DBHelper dbComboHelper = new DBHelper(this);
        dbComboHelper.removeCombos();

        // Get all combos from the database
        alComboList = dbComboHelper.getAllCombos();
        if (alComboList.isEmpty()) {
            // Add default combos if the list is empty
            alComboList = new ArrayList<>();
            alComboList.add(new Combos(0, "Reinforce", new int[]{R.drawable.up, R.drawable.down, R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alComboList.add(new Combos(1, "Resupply", new int[]{R.drawable.down, R.drawable.down, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alComboList.add(new Combos(2, "Eagle Rearm", new int[]{R.drawable.up, R.drawable.up, R.drawable.left, R.drawable.up, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alComboList.add(new Combos(3, "Eagle Airstrike", new int[]{R.drawable.up, R.drawable.right, R.drawable.down, R.drawable.right, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            alComboList.add(new Combos(4, "Eagle 500kg Bomb", new int[]{R.drawable.up, R.drawable.left, R.drawable.down, R.drawable.down, R.drawable.down, R.drawable.transparent, R.drawable.transparent, R.drawable.transparent}));
            dbComboHelper.addCombos(alComboList);
            alComboList = dbComboHelper.getAllCombos();
        }

        // Set up the adapter for RecyclerView
        comboAdapter = new ComboAdapter(alComboList, ComboMainActivity.this);
        rvCombos.setAdapter(comboAdapter);

        Collections.shuffle(alComboList);
        comboAdapter.notifyItemRangeChanged(0, alComboList.size());



        // Set item click listener for RecyclerView items
        comboAdapter.setOnStudentClickListener((position, student) -> {
            // Start CombinationActivity with selected combo details
            Intent colorIntent = new Intent(ComboMainActivity.this, CombinationActivity.class);
            colorIntent.putExtra("student", student);
            int[] imageResources = student.getCombos();
            colorIntent.putExtra("imageResource", imageResources);
            colorIntent.putExtra("name", student.getName());
            startActivity(colorIntent);
            finish();
        });



        // Reset score and correct combo count
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putInt("score", 0);
        editor.putInt("correctCombos", 0);
        editor.apply();

        // Update tvScore text
        tvScore.setText("0");
    }
}