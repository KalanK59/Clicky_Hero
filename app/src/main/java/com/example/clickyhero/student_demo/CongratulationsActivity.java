package com.example.clickyhero.student_demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clickyhero.R;

public class CongratulationsActivity extends AppCompatActivity {
    TextView tvCorrectCombos;
    Button btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_congratulations);

        tvCorrectCombos = findViewById(R.id.tvCorrectCombos);
        btnPrevious = findViewById(R.id.btnPrevious);

        Bundle extras = getIntent().getExtras();
        String score = extras.getString("score", "");
        tvCorrectCombos.setText(score);

        btnPrevious.setOnClickListener(v -> {
            // Start ComboMainActivity
            Intent intent = new Intent(CongratulationsActivity.this, ComboMainActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
        });
        btnPrevious.setOnClickListener(v -> {
            // Start ComboMainActivity
            Intent intent = new Intent(CongratulationsActivity.this, ComboMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // Bring ComboMainActivity to the front
            startActivity(intent);
            finish(); // Close the current activity
        });
    }
}
