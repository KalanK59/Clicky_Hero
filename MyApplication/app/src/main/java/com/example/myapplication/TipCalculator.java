package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TipCalculator extends AppCompatActivity {

    EditText etPrice;
    EditText etTax;
    EditText etTip;

    Button btnCalculate;

    EditText tvTotal;

    Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tip_calculator);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // bind the controls
        etPrice = findViewById(R.id.etPrice);
        etTax = findViewById(R.id.etTax);
        etTip = findViewById(R.id.etTip);

        tvTotal = findViewById(R.id.tvTotal);

        btnCalculate = findViewById(R.id.btnCalculate);

        btnGenerate = findViewById(R.id.btnGenerate);

//Same logic relating to the navigation menu as for the menus itself
//Hard would be how it is displayed - For the project
//Create a menu and attach it to the activity


        // attach the listener and respond to the event
        btnCalculate.setOnClickListener(v -> {
            double price = Double.parseDouble(etPrice.getText().toString());
            double tax = Double.parseDouble(etTax.getText().toString());
            double tip = Double.parseDouble(etTip.getText().toString());

            // calculate the total price
            double totalPrice = price + tax + tip;

            tvTotal.setText("" + totalPrice);
        });

        btnGenerate.setOnClickListener(v -> {
            Intent receivedActivity = new Intent(TipCalculator.this, Resources_12.class);

            receivedActivity.putExtra("key_resources", tvTotal.getText().toString());

            startActivity(receivedActivity);


        });


    }
}