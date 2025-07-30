package com.example.konverensisuhu;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast; // Import for showing messages

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputTempEditText;
    private Spinner conversionSpinner;
    private Button convertButton;
    private TextView resultTextView;

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
        inputTempEditText = findViewById(R.id.edit_text_input_temp);
        conversionSpinner = findViewById(R.id.spinner_conversion_type);
        convertButton = findViewById(R.id.button_convert);
        resultTextView = findViewById(R.id.text_result);

        // Populate the Spinner with conversion options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.conversion_options, // Define this array in res/values/strings.xml
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionSpinner.setAdapter(adapter);

        // Set OnClickListener for the Convert button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void performConversion() {
        String inputStr = inputTempEditText.getText().toString();

        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Please enter a temperature value.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double inputValue = Double.parseDouble(inputStr);
            String selectedOption = conversionSpinner.getSelectedItem().toString();
            double result = 0;
            String unit = ""; // To store the target unit (e.g., "°F", "K")

            // --- Conversion Logic ---
            // You will expand this section based on your specific conversion needs
            if (selectedOption.equals("Celsius to Fahrenheit")) {
                result = (inputValue * 9/5) + 32;
                unit = "°F";
            } else if (selectedOption.equals("Fahrenheit to Celsius")) {
                result = (inputValue - 32) * 5/9;
                unit = "°C";
            } else if (selectedOption.equals("Celsius to Kelvin")) {
                result = inputValue + 273.15;
                unit = "K";
            }
            // Add more conversion types as needed

            resultTextView.setText(String.format("Hasil: %.2f %s", result, unit));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number entered. Please use digits.", Toast.LENGTH_SHORT).show();
            resultTextView.setText("Hasil: Error");
        } catch (Exception e) {
            Toast.makeText(this, "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
            resultTextView.setText("Hasil: Error");
        }
    }
}