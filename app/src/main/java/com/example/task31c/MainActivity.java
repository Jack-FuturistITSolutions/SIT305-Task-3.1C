package com.example.task31c;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Instantiate objects as variables
    EditText nameField;

    // Function to switch between activities/move onto next question
    public void nextQuestion(View view)
    {
        String name = nameField.getText().toString().trim();

        if (!name.isEmpty()) {

            // Show welcome toast
            Toast.makeText(this, "Welcome " + name + "!", Toast.LENGTH_SHORT).show();

            // Move to next activity and pass the name
            Intent intent = new Intent(this, Question1.class);
            intent.putExtra("name", name);
            startActivity(intent);
        } else {
            // Show warning if name is empty
            Toast.makeText(this, "Please enter your name before proceeding...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set the nameField variable
        nameField = findViewById(R.id.nameField);

        // Check if name was passed back from the Summary page and set the nameField to the value
        String name = getIntent().getStringExtra("name");
        if (name != null && !name.isEmpty()) {
            nameField.setText(name);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}