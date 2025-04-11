package com.example.task31c;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    // Instantiate objects as variables
    TextView scoreDisplay;
    ProgressBar progress;
    Button takeNewQuiz, exitApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set variables for results summary
        scoreDisplay = findViewById(R.id.scoreDisplay);
        progress = findViewById(R.id.progressGraph);
        takeNewQuiz = findViewById(R.id.takeNewQuiz);
        exitApp = findViewById(R.id.exitApp);

        // Get data from Intent
        String name = getIntent().getStringExtra("name");
        int score = getIntent().getIntExtra("score", 0);
        int total = 5;

        // Set score text
        scoreDisplay.setText(score + " / " + total);

        // Set progress graph value
        int percent = (int) ((score / (float) total) * 100);
        progress.setProgress(percent);

        // Back to Home Screen
        takeNewQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(Summary.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        });

        // Exit button - Close app if clicked
        exitApp.setOnClickListener(v -> {
            finishAffinity();
        });
    }
}