package com.example.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SiswaAdapter siswaAdapter;
    private List<Siswa> siswaList;
    private Button addButton;
    private Button saveButton; // Deklarasi tombol simpan
    private static final String FILE_NAME = "siswa_data.json"; // Nama file untuk penyimpanan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        saveButton = findViewById(R.id.saveButton); // Inisialisasi tombol simpan

        siswaList = new ArrayList<>();
        loadData(); // Muat data saat aplikasi dimulai

        if (siswaList.isEmpty()) { // Jika tidak ada data tersimpan, tambahkan data dummy
            siswaList.add(new Siswa("Budi Santoso", "1001", "X RPL 1", "Jl. Mawar No. 10", "081234567890"));
            siswaList.add(new Siswa("Ani Lestari", "1002", "XI TKJ 2", "Jl. Melati No. 5", "089876543210"));
            siswaList.add(new Siswa("Cahyo Nugroho", "1003", "XII IPA 3", "Jl. Anggrek No. 22", "087654321098"));
            siswaList.add(new Siswa("Dewi Rahayu", "1004", "X MM 1", "Jl. Dahlia No. 7", "085678901234"));
        }


        siswaAdapter = new SiswaAdapter(siswaList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(siswaAdapter);

        // Menyiapkan listener untuk tombol Tambah Item
        addButton.setOnClickListener(new View.OnClickListener() {
            int counter = siswaList.size() + 1; // Mulai counter dari ukuran list yang ada
            @Override
            public void onClick(View v) {
                Siswa newSiswa = new Siswa(
                        "Siswa Baru " + counter,
                        "900" + counter,
                        "Kelas Baru",
                        "Alamat Baru",
                        "08000000000" + counter
                );
                siswaAdapter.addSiswa(newSiswa);
                recyclerView.scrollToPosition(siswaList.size() - 1);
                counter++;
                Toast.makeText(MainActivity.this, "Item ditambahkan!", Toast.LENGTH_SHORT).show();
            }
        });

        // Menyiapkan listener untuk tombol Simpan Data
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        // Menyiapkan listener untuk tombol hapus pada item RecyclerView
        siswaAdapter.setOnItemClickListener(new SiswaAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                siswaAdapter.removeSiswa(position);
                Toast.makeText(MainActivity.this, "Item dihapus!", Toast.LENGTH_SHORT).show();
                saveData(); // Simpan data setelah penghapusan
            }
        });
    }

    // Metode untuk menyimpan data ke file JSON
    private void saveData() {
        Gson gson = new Gson();
        String json = gson.toJson(siswaList);

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal menyimpan data.", Toast.LENGTH_SHORT).show();
            Log.e("MainActivity", "Error saving data: " + e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Metode untuk memuat data dari file JSON
    private void loadData() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = openFileInput(FILE_NAME);
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Siswa>>() {}.getType();
            List<Siswa> loadedList = gson.fromJson(sb.toString(), type);

            if (loadedList != null) {
                siswaList.clear(); // Hapus data dummy jika ada
                siswaList.addAll(loadedList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MainActivity", "Error loading data: " + e.getMessage());
            // Ini akan sering terjadi saat aplikasi pertama kali dijalankan dan file belum ada
        } finally {
            try {
                if (br != null) br.close();
                if (isr != null) isr.close();
                if (fis != null) fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData(); // Simpan data secara otomatis saat aplikasi di-pause
    }
}