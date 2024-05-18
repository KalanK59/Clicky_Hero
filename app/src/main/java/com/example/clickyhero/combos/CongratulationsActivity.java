package com.example.clickyhero.combos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clickyhero.R;

public class CongratulationsActivity extends AppCompatActivity {
    // Declare UI components
    TextView tvCorrectCombos;
    Button btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_congratulations);

        // Initialize UI components
        tvCorrectCombos = findViewById(R.id.tvCorrectCombos);
        btnPrevious = findViewById(R.id.btnPrevious);

        // Get score from the intent extras and display it
        Bundle extras = getIntent().getExtras();
        String score = extras.getString("score", "");
        tvCorrectCombos.setText(score);

        // Set onClickListener for the "Previous" button
        btnPrevious.setOnClickListener(v -> {
            // Start ComboMainActivity
            Intent intent = new Intent(CongratulationsActivity.this, ComboMainActivity.class);
            // Bring ComboMainActivity to the front
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            // Close the current activity
            finish();
        });
    }
}