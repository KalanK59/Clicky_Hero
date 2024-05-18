package com.example.clickyhero.student_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clickyhero.R;

import java.util.Arrays;

public class CombinationActivity extends AppCompatActivity {
    TextView tvUpdate;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8;
    ImageButton btnUp, btnDown, btnLeft, btnRight;
    int[] imageResources;
    boolean[] pressStatus;

    ImageView[] comboIcons;
    int pressStatusIndex = 0;
    int countNonTransparentImages = 0;
    private Combos selectedCombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This is what hides the action bar.

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_combination);

        tvUpdate = findViewById(R.id.tvUpdate);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);

        btnUp = findViewById(R.id.imageBtnUp);
        btnDown = findViewById(R.id.imageBtnDown);
        btnLeft = findViewById(R.id.imageBtnLeft);
        btnRight = findViewById(R.id.imageBtnRight);

        comboIcons = new ImageView[8];

        Bundle extras = getIntent().getExtras();
        selectedCombo = (Combos) extras.getSerializable("student");

        String name = extras.getString("name", "");
        tvUpdate.setText(name);

        imageResources = extras.getIntArray("imageResource");
        if (imageResources != null && imageResources.length >= 8) {
            setImageIfNotTransparent(image1, imageResources[0]);
            setImageIfNotTransparent(image2, imageResources[1]);
            setImageIfNotTransparent(image3, imageResources[2]);
            setImageIfNotTransparent(image4, imageResources[3]);
            setImageIfNotTransparent(image5, imageResources[4]);
            setImageIfNotTransparent(image6, imageResources[5]);
            setImageIfNotTransparent(image7, imageResources[6]);
            setImageIfNotTransparent(image8, imageResources[7]);

            // Initialize press status for each image button
            pressStatus = new boolean[countNonTransparentImages];
            Arrays.fill(pressStatus, false);

            // Set onClickListeners for each image button
            btnUp.setOnClickListener(v -> handlePress(comboIcons[pressStatusIndex], R.drawable.up));
            btnDown.setOnClickListener(v -> handlePress(comboIcons[pressStatusIndex], R.drawable.down));
            btnLeft.setOnClickListener(v -> handlePress(comboIcons[pressStatusIndex], R.drawable.left));
            btnRight.setOnClickListener(v -> handlePress(comboIcons[pressStatusIndex], R.drawable.right));
        }
    }


    private void setImageIfNotTransparent(ImageView imageView, int resourceId) {

        if (resourceId != R.drawable.transparent) {
            imageView.setImageResource(resourceId);
            comboIcons[countNonTransparentImages] = imageView;
            countNonTransparentImages++;
        }
        else {
            imageView.setVisibility(ImageView.INVISIBLE);
        }
    }

  /* private void handlePress(ImageView imageView, int sentBtnId) {
        int correctBtnId = imageResources[pressStatusIndex];
        Log.d("DEBUG", "correctBtnId: " + correctBtnId);
        Log.d("DEBUG", "sentBtnId: " + sentBtnId);


        // Check if the current button press is correct (in sequence)
        if (sentBtnId == correctBtnId) {
            // Set correct press status (gold star)
            imageView.setImageResource(android.R.drawable.btn_star_big_on);
            pressStatus[pressStatusIndex] = true;

        } else {
            // Set incorrect press status (grey star)
            imageView.setImageResource(android.R.drawable.btn_star_big_off);
            // Reset press status for all buttons if a mistake is made
            Arrays.fill(pressStatus, false);
        }

        // Update combo status in database


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int score = sharedPreferences.getInt("score", 0);
        // Check if all buttons have been pressed correctly
        if (pressStatusIndex == pressStatus.length - 1) {
            // Assuming pressStatus is your array containing button press statuses
            if (areAllTrue(pressStatus)) {
                // Increment score
                score++;
                editor.putInt("score", score);

            }
            editor.apply();

            // Finish the activity and return to the previous one
            Log.d("ComboUpdate", "pressStatusIndex: " + pressStatus[pressStatusIndex]);
            DBHelper dbComboHelper = new DBHelper(this);
            dbComboHelper.updateComboStatus(selectedCombo, pressStatus[pressStatusIndex]);

            finish();

        }
        pressStatusIndex++;
    } */

    private void handlePress(ImageView imageView, int sentBtnId) {
        int correctBtnId = imageResources[pressStatusIndex];
        Log.d("DEBUG", "correctBtnId: " + correctBtnId);
        Log.d("DEBUG", "sentBtnId: " + sentBtnId);

        // Check if the current button press is correct (in sequence)
        if (sentBtnId == correctBtnId) {
            // Set correct press status (gold star)
            imageView.setImageResource(android.R.drawable.btn_star_big_on);
            pressStatus[pressStatusIndex] = true;
        } else {
            // Set incorrect press status (grey star)
            imageView.setImageResource(android.R.drawable.btn_star_big_off);
            pressStatus[pressStatusIndex] = false; // Mark the press as incorrect
        }

        pressStatusIndex++;

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int score = sharedPreferences.getInt("score", 0);

        // Check if all buttons have been pressed
        if (pressStatusIndex == pressStatus.length) {
            // Check if all buttons were pressed correctly
            boolean comboCorrect = areAllTrue(pressStatus);
            if (comboCorrect) {
                // Increment score if all buttons pressed correctly
                score++;
                editor.putInt("score", score);

            }

            editor.apply();

            // Finish the activity and return to the previous one
            DBHelper dbComboHelper = new DBHelper(this);
            dbComboHelper.updateComboStatus(selectedCombo, comboCorrect ? 1 : -1);

            // Finish the activity and return to the previous one
            startActivity(new Intent(this, ComboMainActivity.class));
            finish();
        }
    }

    private boolean areAllTrue(boolean[] array) {
        for (boolean value : array) {
            if (!value) {
                return false;
            }
        }
        return true;
    }
}