package com.example.clickyhero.student_demo;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.clickyhero.R;

import java.util.Arrays;

public class CombinationActivity extends AppCompatActivity {

    TextView tvUpdate;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8;
    ImageButton btnUp, btnDown, btnLeft, btnRight;
    int[] imageResources;
    boolean[] pressStatus;

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

        Bundle extras = getIntent().getExtras();

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
            pressStatus = new boolean[4];
            Arrays.fill(pressStatus, false);

            // Set onClickListeners for each image button
            btnUp.setOnClickListener(v -> handlePress(image1, 0));
            btnDown.setOnClickListener(v -> handlePress(image2, 1));
            btnLeft.setOnClickListener(v -> handlePress(image3, 2));
            btnRight.setOnClickListener(v -> handlePress(image4, 3));
        }
    }

    private void setImageIfNotTransparent(ImageView imageView, int resourceId) {
        if (resourceId != R.drawable.transparent) {
            imageView.setImageResource(resourceId);
        }
        else {
            imageView.setVisibility(ImageView.INVISIBLE);
        }
    }

    private void handlePress(ImageView imageView, int index) {
        // Check if the current button press is correct (in sequence)
        if (index == 0 || (index > 0 && pressStatus[index - 1])) {
            // Set correct press status (gold star)
            imageView.setImageResource(android.R.drawable.btn_star_big_on);
            pressStatus[index] = true;

            // Check if all buttons have been pressed correctly
            if (index == pressStatus.length - 1 && pressStatus[index]) {
                // All buttons pressed correctly, close the activity
                finish();
            }
        } else {
            // Set incorrect press status (grey star)
            imageView.setImageResource(android.R.drawable.btn_star_big_off);
            // Reset press status for all buttons if a mistake is made
            Arrays.fill(pressStatus, false);
        }
    }
}