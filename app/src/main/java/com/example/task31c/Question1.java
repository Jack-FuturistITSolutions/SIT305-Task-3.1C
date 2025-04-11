package com.example.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Question1 extends AppCompatActivity {

    // Instantiate objects as variables
    TextView nameDisplay;
    QuizContent quizContent;

    // Function to switch between activities/move onto next question
    public void nextQuestion(View view) {
        String name = getIntent().getStringExtra("name");
        int score = getIntent().getIntExtra("score", 0);

        // Increment score if this answer was correct
        if (quizContent.answerStatusCheck()) {
            score++;
        }

        Intent intent = new Intent(this, Question2.class);
        intent.putExtra("name", name);
        intent.putExtra("score", score);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.questions_layout);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get name from Intent and display
        String name = getIntent().getStringExtra("name");
        nameDisplay = findViewById(R.id.userNameDisplay);
        nameDisplay.setText("Welcome, " + name + "!");

        // Set up the question and answers
        quizContent = new QuizContent(this);
        quizContent.setupQuestion(
                R.id.questionText,
                "What is 87 + 23?",
                new int[]{R.id.option1, R.id.option2, R.id.option3},
                new String[]{"1", "42", "100"},
                R.id.option3
        );

        // Setup the submit button
        quizContent.setSubmitButton(R.id.submitButton);

        // Setup the progress bar and counter
        quizContent.updateProgressBar(R.id.progressBar, 1, 5);
        TextView counter = findViewById(R.id.questionCounter);
        counter.setText("1/5");
    }
}
