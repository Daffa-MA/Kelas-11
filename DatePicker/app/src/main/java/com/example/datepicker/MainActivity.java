package com.example.datepicker;

import android.app.DatePickerDialog; // Import DatePickerDialog
import android.os.Bundle;
import android.view.View; // Import View
import android.widget.Button; // Import Button
import android.widget.EditText; // Import EditText
import android.widget.TextView; // Import TextView (if you use textViewTitle)
import android.widget.DatePicker; // Import DatePicker
import java.text.SimpleDateFormat; // Import SimpleDateFormat
import java.util.Calendar; // Import Calendar
import java.util.Locale; // Import Locale

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDate;
    private Button buttonPickDate;
    private Calendar calendar; // Untuk menyimpan tanggal yang dipilih

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

        // Inisialisasi elemen UI
        editTextDate = findViewById(R.id.editTextDate);
        buttonPickDate = findViewById(R.id.buttonPickDate);

        // Inisialisasi objek Calendar dengan tanggal saat ini
        calendar = Calendar.getInstance();

        // Buat OnDateSetListener untuk menerima tanggal yang dipilih dari DatePickerDialog
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Set tanggal yang dipilih ke objek Calendar
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Update EditText dengan tanggal yang diformat
                updateEditText();
            }
        };

        // Tambahkan OnClickListener ke EditText
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tampilkan DatePickerDialog saat EditText diklik
                new DatePickerDialog(MainActivity.this,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Tambahkan OnClickListener ke Button
        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tampilkan DatePickerDialog saat Button diklik
                new DatePickerDialog(MainActivity.this,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Opsional: Atur tanggal awal di EditText saat pertama kali aplikasi dibuka
        updateEditText();
    }

    // Metode helper untuk memperbarui EditText dengan tanggal dari objek Calendar
    private void updateEditText() {
        String dateFormat = "dd MMMM yyyy"; // Format tanggal: 29 Juli 2025
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        editTextDate.setText(sdf.format(calendar.getTime()));
    }
}