package com.example.my;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextView;
    private Button incrementButton;
    private Button decrementButton;
    private int counter = 0; // Initial value for the counter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enables edge-to-edge display
        setContentView(R.layout.activity_main); // Sets the layout for this activity

        // Apply window insets to main view for system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        counterTextView = findViewById(R.id.text_counter);
        incrementButton = findViewById(R.id.button_increment);
        decrementButton = findViewById(R.id.button_decrement);

        // Set initial counter text
        counterTextView.setText(String.valueOf(counter));

        // Set OnClickListener for the Increment button
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++; // Increment the counter
                counterTextView.setText(String.valueOf(counter)); // Update the TextView
            }
        });

        // Set OnClickListener for the Decrement button
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--; // Decrement the counter
                counterTextView.setText(String.valueOf(counter)); // Update the TextView
            }
        });
    }
}