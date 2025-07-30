package com.example.intentactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    // private List<Product> productList; // No longer needed if fetching from static list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.product_list_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        // Get the saved products from ProductActivity
        List<Product> productsToDisplay = ProductActivity.getSavedProducts();
        if (productsToDisplay == null) {
            productsToDisplay = new ArrayList<>(); // Ensure it's not null
        }

        productAdapter = new ProductAdapter(productsToDisplay); // Pass the retrieved list
        recyclerViewProducts.setAdapter(productAdapter);

        // Optional: Update the adapter if the list changes (e.g., when returning to this activity)
        // This is more relevant if you're not using a static list for data persistence.
        // For static list, the list itself is updated in ProductActivity.
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list when returning to this activity
        if (productAdapter != null) {
            List<Product> updatedProducts = ProductActivity.getSavedProducts();
            if (updatedProducts != null) {
                productAdapter.updateProducts(updatedProducts);
            }
        }
    }
}