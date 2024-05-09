package com.example.clickyhero;

public class ComboActivity extends AppCompatActivity {
    private ImageButton[] arrowButtons;
    private ImageView[] resultStars;
    private String selectedCombo;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        selectedCombo = getIntent().getStringExtra("COMBO_NAME");
        arrowButtons = new ImageButton[]{findViewById(R.id.arrowButton1), ...};
        resultStars = new ImageView[]{findViewById(R.id.star1), ...};

        for (ImageButton button : arrowButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkCombo(v.getId());
                }
            });
        }
    }
}
