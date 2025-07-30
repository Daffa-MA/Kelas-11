package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Import Toolbar
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        // Set up the toolbar (if you use it)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Set the toolbar as the activity's action bar

        // Find buttons by their new IDs
        Button buttonToast = findViewById(R.id.button_toast);
        Button buttonAlertDialog = findViewById(R.id.button_alert_dialog);
        Button buttonAlertDialogActions = findViewById(R.id.button_alert_dialog_actions);

        // Set OnClickListener for the Toast button
        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastMessage("This is a sleek Toast message!");
            }
        });

        // Set OnClickListener for the Simple Alert Dialog button
        buttonAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleAlertDialog("Simple Alert", "This is a simple alert dialog message.");
            }
        });

        // Set OnClickListener for the Alert Dialog with Actions button
        buttonAlertDialogActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogWithActions("Action Required", "Do you want to proceed with this action?",
                        "Yes", "No");
            }
        });
    }

    /**
     * Helper method to show a Toast message.
     * @param message The message to display in the Toast.
     */
    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method to show a simple AlertDialog.
     * @param title The title of the dialog.
     * @param message The message content of the dialog.
     */
    private void showSimpleAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null) // "null" makes it just dismiss the dialog
                .show();
    }

    /**
     * Helper method to show an AlertDialog with positive and negative actions.
     * @param title The title of the dialog.
     * @param message The message content of the dialog.
     * @param positiveButtonText The text for the positive button.
     * @param negativeButtonText The text for the negative button.
     */
    private void showAlertDialogWithActions(String title, String message,
                                            String positiveButtonText, String negativeButtonText) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Positive action taken!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Negative action taken!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss(); // Dismiss the dialog
                    }
                })
                .setCancelable(false) // Prevents dismissal by tapping outside or pressing back
                .show();
    }
}