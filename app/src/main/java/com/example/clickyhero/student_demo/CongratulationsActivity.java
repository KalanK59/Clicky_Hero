package com.example.clickyhero.student_demo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        String name = extras.getString("score", "");
        tvCorrectCombos.setText(name);

        btnPrevious.setOnClickListener(v -> {
            finish();
        });

    }
}