package com.example.intentactivity;

import android.content.Intent; // Import Intent
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList; // Used if you want to pass data
import java.util.List; // Used if you want to pass data

public class ProductActivity extends AppCompatActivity {

    private EditText editTextProductName;
    private EditText editTextProductPrice;
    private EditText editTextProductStock;
    private Button buttonSaveProduct;
    private Button buttonViewProducts;

    // Optional: Untuk menyimpan sementara produk yang ditambahkan
    // Dalam aplikasi nyata, ini akan disimpan di database atau persistent storage
    private static List<Product> savedProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.product_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductPrice = findViewById(R.id.editTextProductPrice);
        editTextProductStock = findViewById(R.id.editTextProductStock);
        buttonSaveProduct = findViewById(R.id.buttonSaveProduct);
        buttonViewProducts = findViewById(R.id.buttonViewProducts);

        buttonSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = editTextProductName.getText().toString().trim();
                String priceStr = editTextProductPrice.getText().toString().trim();
                String stockStr = editTextProductStock.getText().toString().trim();

                if (productName.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
                    Toast.makeText(ProductActivity.this, "Mohon lengkapi semua data produk", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        double productPrice = Double.parseDouble(priceStr);
                        int productStock = Integer.parseInt(stockStr);

                        Product newProduct = new Product(productName, productPrice, productStock);
                        savedProducts.add(newProduct); // Simpan produk ke list sementara

                        Toast.makeText(ProductActivity.this, "Produk: " + productName + " berhasil disimpan!", Toast.LENGTH_SHORT).show();

                        // Clear input fields
                        editTextProductName.setText("");
                        editTextProductPrice.setText("");
                        editTextProductStock.setText("");
                    } catch (NumberFormatException e) {
                        Toast.makeText(ProductActivity.this, "Harga atau Stok tidak valid. Masukkan angka.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonViewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, ProductListActivity.class);
                // Optional: Anda bisa mengirim data produk ke ProductListActivity
                // Namun, untuk contoh RecyclerView sederhana ini, ProductListActivity
                // akan membuat data dummy sendiri atau mengambil dari sumber data.
                // Jika ingin mengirim data, Anda perlu membuat Product class Parcelable/Serializable.
                startActivity(intent);
            }
        });
    }

    // Metode untuk mendapatkan daftar produk yang disimpan
    // Ini adalah cara *sederhana* untuk berbagi data antar Activity untuk DEMO.
    // Untuk aplikasi yang lebih besar, gunakan ViewModel, SQLite, Room, atau API.
    public static List<Product> getSavedProducts() {
        return savedProducts;
    }
}