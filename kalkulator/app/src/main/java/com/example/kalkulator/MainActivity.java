package com.example.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Import for showing messages

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private TextView textResult;
    private Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        editTextNumber1 = findViewById(R.id.edit_text_number1);
        editTextNumber2 = findViewById(R.id.edit_text_number2);
        textResult = findViewById(R.id.text_result);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubtract = findViewById(R.id.button_subtract);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonDivide = findViewById(R.id.button_divide);

        // Set OnClickListeners for the buttons
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult('+');
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult('-');
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult('*');
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult('/');
            }
        });
    }

    private void calculateResult(char operation) {
        String sNum1 = editTextNumber1.getText().toString();
        String sNum2 = editTextNumber2.getText().toString();

        if (sNum1.isEmpty() || sNum2.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(sNum1);
            double num2 = Double.parseDouble(sNum2);
            double result = 0;

            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        textResult.setText("Error"); // Indicate error
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            textResult.setText(String.valueOf(result)); // Display the result
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Invalid number format", Toast.LENGTH_SHORT).show();
            textResult.setText("Error"); // Indicate error
        }
    }
}