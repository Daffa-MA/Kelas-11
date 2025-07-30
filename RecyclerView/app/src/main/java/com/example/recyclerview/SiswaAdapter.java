package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton; // Import ImageButton
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder> {

    private List<Siswa> siswaList;
    private OnItemClickListener listener; // Deklarasi listener

    // Interface untuk menangani klik item
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    // Metode untuk mengatur listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SiswaAdapter(List<Siswa> siswaList) {
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public SiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_siswa, parent, false);
        return new SiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaViewHolder holder, int position) {
        Siswa siswa = siswaList.get(position);
        holder.tvNamaSiswa.setText(siswa.getNama());
        holder.tvNisSiswa.setText("NIS: " + siswa.getNis());
        holder.tvKelasSiswa.setText("Kelas: " + siswa.getKelas());
        holder.tvAlamatSiswa.setText("Alamat: " + siswa.getAlamat());
        holder.tvTeleponSiswa.setText("Telepon: " + siswa.getTelepon());
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    // Metode untuk menambahkan item baru
    public void addSiswa(Siswa newSiswa) {
        siswaList.add(newSiswa);
        notifyItemInserted(siswaList.size() - 1);
    }

    // Metode untuk menghapus item
    public void removeSiswa(int position) {
        siswaList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, siswaList.size()); // Penting untuk memperbarui posisi setelah penghapusan
    }


    // Kelas ViewHolder
    public class SiswaViewHolder extends RecyclerView.ViewHolder { // Hapus 'static' agar bisa mengakses listener dari adapter
        TextView tvNamaSiswa, tvNisSiswa, tvKelasSiswa, tvAlamatSiswa, tvTeleponSiswa;
        ImageButton deleteButton; // Deklarasi ImageButton

        public SiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaSiswa = itemView.findViewById(R.id.tv_namaSiswa);
            tvNisSiswa = itemView.findViewById(R.id.tv_nisSiswa);
            tvKelasSiswa = itemView.findViewById(R.id.tv_kelasSiswa);
            tvAlamatSiswa = itemView.findViewById(R.id.tv_alamatSiswa);
            tvTeleponSiswa = itemView.findViewById(R.id.tv_teleponSiswa);
            deleteButton = itemView.findViewById(R.id.deleteButton); // Inisialisasi ImageButton

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}