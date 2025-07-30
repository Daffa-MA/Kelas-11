package com.example.recyclerview;

public class Siswa {
    private String nama;
    private String nis;
    private String kelas;
    private String alamat;
    private String telepon;

    public Siswa(String nama, String nis, String kelas, String alamat, String telepon) {
        this.nama = nama;
        this.nis = nis;
        this.kelas = kelas;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    // Getters
    public String getNama() {
        return nama;
    }

    public String getNis() {
        return nis;
    }

    public String getKelas() {
        return kelas;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    // Anda bisa menambahkan setters jika Anda perlu memodifikasi data setelah pembuatan
    public void setNama(String nama) {
        this.nama = nama;
    }
    // ... similarly for others
}