package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.entity.LogMakanEntity;
import com.example.myapplication.model.MakanModel;

public class TambahList extends AppCompatActivity {
    private EditText edtWaktu, edtMakanan;
    private Button btnHapus;
    private LogMakanEntity logMakanEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_list);

        this.edtWaktu = this.findViewById(R.id.edt_waktu);
        this.edtMakanan = this.findViewById(R.id.edt_makanan);
        this.btnHapus = this.findViewById(R.id.btn_hapus);
        // Cek apakah ada Extra di intentnya.
        Intent intentTitipan = this.getIntent();
        int makanId = intentTitipan.getIntExtra("makanId", -1);

        // Apabila ada maka ambil data kontak dari DB sesuai dengan Id yang ada di Extra-nya Intent.
        if(makanId != -1)
        {
            // Ambil dari database..
            this.muatData(makanId);
        }

        this.sesuaikanTampilan();

    }
    private void sesuaikanTampilan() {
        if(this.logMakanEntity == null)
            this.btnHapus.setVisibility(View.GONE);
        else
            this.btnHapus.setVisibility(View.VISIBLE);
    }

    private void muatData(int makanId)
    {
        MakanModel mMakan = new MakanModel(this);

        LogMakanEntity k = mMakan.cariBerdasarkanId(makanId);

        this.edtWaktu.setText(k.getWaktu());
        this.edtMakanan.setText(k.getMakanan());

        this.logMakanEntity = k;
    }

    public void btnKembali_onClick(View view) {
        this.finish();
    }

    public void btnHapus_onClick(View view) {
        MakanModel mMakan = new MakanModel(this);

        mMakan.hapusMenu(this.logMakanEntity);

        this.edtWaktu.setText("");
        this.edtMakanan.setText("");

        this.logMakanEntity = null;

        this.sesuaikanTampilan();

        Toast.makeText(this, "Data telah dihapus..", Toast.LENGTH_SHORT).show();
    }

    public void btnSimpan_onClick(View view) {
        // Ambil data dari komponen
        String waktu = this.edtWaktu.getText().toString();
        String makanan = this.edtMakanan.getText().toString();

        // Panggil Modelnya
        MakanModel mMakan = new MakanModel(this);

        if(this.logMakanEntity == null) // Jika tidak ada kontak terpilih berarti saat ini adalah window untuk menambah/mengurange data Pak..
        {
            // Masukkan ke Entity-nya
            LogMakanEntity k = new LogMakanEntity();
            k.setWaktu(waktu);
            k.setMakanan(makanan);

            mMakan.tambahMenu(k);
        }
        else
        {
            this.logMakanEntity.setWaktu(waktu);
            this.logMakanEntity.setMakanan(makanan);

            mMakan.perbaruiMenu(this.logMakanEntity);
        }

        Toast.makeText(this, "Data telah disimpan..", Toast.LENGTH_SHORT).show();
    }
}