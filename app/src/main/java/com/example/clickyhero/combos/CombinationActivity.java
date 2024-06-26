package com.example.clickyhero.combos;

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
    // Declare UI components
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

        // Remove the title and set full-screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // Hide the action bar

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_combination);

        // Initialize UI components
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

        // Get data from the intent
        Bundle extras = getIntent().getExtras();
        selectedCombo = (Combos) extras.getSerializable("student");
        String name = extras.getString("name", "");
        tvUpdate.setText(name);

        imageResources = extras.getIntArray("imageResource");
        if (imageResources != null && imageResources.length >= 8) {
            // Set images if they are not transparent
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

    // Set image if it is not transparent
    private void setImageIfNotTransparent(ImageView imageView, int resourceId) {
        if (resourceId != R.drawable.transparent) {
            imageView.setImageResource(resourceId);
            comboIcons[countNonTransparentImages] = imageView;
            countNonTransparentImages++;
        } else {
            imageView.setVisibility(ImageView.INVISIBLE);
        }
    }

    // Handle button press
    private void handlePress(ImageView imageView, int sentBtnId) {
        int correctBtnId = imageResources[pressStatusIndex];
        //Checks in the logcat for the correct and sent button ids.
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

        // Update the press status index
        pressStatusIndex++;

        // Get and update the score in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int correctCombos = sharedPreferences.getInt("correctCombos", 0);
        int score = sharedPreferences.getInt("score", 0);

        // Check if all buttons have been pressed
        if (pressStatusIndex == pressStatus.length) {
            // Check if all buttons were pressed correctly
            boolean comboCorrect = areAllTrue(pressStatus);
            if (comboCorrect) {
                // Increment score if all buttons pressed correctly
                score++;
                editor.putInt("score", score);
                editor.apply();

                // Increment the correct combo count
                correctCombos++;
                editor.putInt("correctCombos", correctCombos);
                editor.apply();

                Log.d("DEBUG", "Correct Combos: " + correctCombos);  // Log correct combo count

            }

            // Update combo status in the database
            DBHelper dbComboHelper = new DBHelper(this);
            dbComboHelper.updateComboStatus(selectedCombo, comboCorrect ? 1 : -1);

            // Start the CongratulationsActivity if 5 combos are correct
            if (correctCombos == 5) {
                Log.d("DEBUG", "Starting CongratulationsActivity");  // Log starting intent
                Intent congratsIntent = new Intent(this, CongratulationsActivity.class);
                congratsIntent.putExtra("score", String.valueOf(score));
                startActivity(congratsIntent);
                finish();  // Close the current activity
                return;  // Exit the method
            }

            // Finish the activity and return to the previous one
            startActivity(new Intent(this, ComboMainActivity.class));
            finish();
        }
    }

    // Check if all values in the array are true
    private boolean areAllTrue(boolean[] array) {
        for (boolean value : array) {
            if (!value) {
                return false;
            }
        }
        return true;
    }
}
