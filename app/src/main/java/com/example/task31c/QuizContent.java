package com.example.task31c;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class QuizContent {
    private final Activity activity;
    private Button[] optionButtons;
    private Button submitButton;
    private int correctButtonId;
    private int selectedOptionId = -1;
    private boolean answerStatus = false;

    public boolean answerStatusCheck() {
        return answerStatus;
    }

    public QuizContent(Activity activity) {
        this.activity = activity;
    }

    public void setupQuestion(int questionTextId, String question, int[] optionIds, String[] options, int correctOptionId)
    {
        // Set the question TextView object and setup the TextView to display the question
        TextView questionTextView = activity.findViewById(questionTextId);
        questionTextView.setText(question);

        // Collect applicable answer button ids and set the correct option id supplied
        this.optionButtons = new Button[optionIds.length];
        this.correctButtonId = correctOptionId;

        // Apply features to the buttons and listen for button clicks
        for (int i = 0; i < optionIds.length; i++) {
            Button button = activity.findViewById(optionIds[i]);
            button.setText(options[i]);
            button.setEnabled(true);
            int defaultColor = ContextCompat.getColor(activity, android.R.color.darker_gray);
            button.setBackgroundColor(defaultColor);

            button.setOnClickListener(view -> {
                resetOptionColors();
                view.setBackgroundColor(Color.DKGRAY);
                selectedOptionId = view.getId();
            });

            optionButtons[i] = button;
        }
    }

    // Set up the submit button and apply logic for once clicked
    public void setSubmitButton(int submitButtonId) {
        submitButton = activity.findViewById(submitButtonId);
        submitButton.setEnabled(true);

        // List for click and check answer
        submitButton.setOnClickListener(v -> {
            if (selectedOptionId == -1) {
                Toast.makeText(activity, "Please select an answer...", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedOptionId == correctButtonId) {
                activity.findViewById(correctButtonId).setBackgroundColor(Color.GREEN);
                Toast.makeText(activity, "Correct!", Toast.LENGTH_SHORT).show();
                answerStatus = selectedOptionId == correctButtonId;
            } else {
                activity.findViewById(selectedOptionId).setBackgroundColor(Color.RED);
                activity.findViewById(correctButtonId).setBackgroundColor(Color.GREEN);
                Toast.makeText(activity, "Incorrect!", Toast.LENGTH_SHORT).show();
            }

            for (Button button : optionButtons) button.setEnabled(false);
            submitButton.setEnabled(false);
        });
    }

    // Reset option colors for the unselected buttons
    private void resetOptionColors() {
        int defaultColor = ContextCompat.getColor(activity, android.R.color.darker_gray);
        for (Button btn : optionButtons) {
            btn.setBackgroundColor(defaultColor);
        }
    }

    // Control the progress bar status
    public void updateProgressBar(int progressBarId, int currentQuestion, int totalQuestions) {
        ProgressBar progressBar = activity.findViewById(progressBarId);
        int progressPercent = (int) ((currentQuestion / (float) totalQuestions) * 100);
        progressBar.setProgress(progressPercent);
    }
}
