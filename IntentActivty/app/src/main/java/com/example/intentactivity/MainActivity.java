package com.example.intentactivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.Intent; // <--- ADD THIS LINE

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextText;
    private Button buttonBarang;
    private Button buttonPenjualan;
    private Button buttonPembelian;
    private TextView textViewTitle;

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

        textViewTitle = findViewById(R.id.textViewTitle);
        editTextText = findViewById(R.id.editTextText);
        buttonBarang = findViewById(R.id.button_barang);
        buttonPenjualan = findViewById(R.id.button_penjualan);
        buttonPembelian = findViewById(R.id.button_pembelian);

        buttonBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is line 46:
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        // Add listeners for other buttons if you haven't already
        // buttonPenjualan.setOnClickListener(...);
        // buttonPembelian.setOnClickListener(...);
    }
}