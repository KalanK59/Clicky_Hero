package com.example.clickyhero;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class mainClickyHero extends AppCompatActivity {

    RecyclerView rvCombo;

    ComboAdapter comboAdapter;
    ArrayList<Combination> comboList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clicky_hero);

        rvCombo = findViewById(R.id.rvCombo);
        rvCombo.setLayoutManager(new LinearLayoutManager(this));

        comboList = (ArrayList<Combination>) generateCombinations(); // Implement this method to generate your combinations
        comboAdapter = new ComboAdapter(comboList);
        rvCombo.setAdapter(comboAdapter);
    }

    // Implement this method to generate your combinations
    private List<Combination> generateCombinations() {
        List<Combination> combinations = new ArrayList<>();
        Random random = new Random();

        // Define the combinations
        List<String> sequences = Arrays.asList(
                "ABCD", "WXYZ", "LMNOP", "QRSTUV", "EFGHIJK"
        );

        // Define the images
        List<Integer> imageResourceIds = Arrays.asList(
                R.drawable.down, R.drawable.left, R.drawable.up, R.drawable.right
        );

        // Shuffle the sequences and image resource IDs
        Collections.shuffle(sequences);
        Collections.shuffle(imageResourceIds);

        // Create combinations
        for (int i = 0; i < 5; i++) {
            combinations.add(new Combination("Combination " + (i + 1), sequences.get(i), imageResourceIds.get(random.nextInt(imageResourceIds.size()))));
        }

        return combinations;
    }
}