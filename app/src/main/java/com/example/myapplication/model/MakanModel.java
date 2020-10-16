package com.example.myapplication.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.myapplication.entity.LogMakanEntity;
import com.example.myapplication.helper.DbHelper;

import java.util.ArrayList;

public class MakanModel {
    private Context context;
    private DbHelper db;

    public MakanModel(Context context)
    {
        this.context = context;
        this.db = new DbHelper(this.context);
    }

    public void tambahMenu(LogMakanEntity k)
    {
        String waktu = k.getWaktu();
        String makanan = k.getMakanan();

        // Rangkai ke dalam SQL
        // Contoh: INSERT INTO kontak (nama, nomor_telepon) VALUES ('Yoppy', '085-123-456')
        String sql = "INSERT INTO kontak (nama, nomor_telepon) VALUES ('" + waktu + "', '" + makanan + "')";

        // Langsung lempar ke dbHelper
        this.db.tulisData(sql);
    }

    public void hapusMenu(LogMakanEntity k)
    {
        // Buat SQL untuk hapus
        String sql = "DELETE FROM menu WHERE id = '" + k.getId() + "'";

        // Panggil db.tulisData(sql)
        db.tulisData(sql);
    }

    public void perbaruiMenu(LogMakanEntity k)
    {
        int id = k.getId();
        String waktu = k.getWaktu();
        String makanan = k.getMakanan();

        // Buat SQL untuk update
        String sql = "UPDATE menu SET waktu = '" + waktu + "', makanan = '" + makanan + "' WHERE id = '" + id + "'";

        // Tulis ke DB
        db.tulisData(sql);
    }

    public ArrayList<LogMakanEntity> ambilSemuaKontak()
    {
        String sql = "SELECT * FROM menu";

        Cursor c = this.db.bacaData(sql);

        // Ubah cursor menjadi ArrayList
        ArrayList<LogMakanEntity> hasil = new ArrayList<>();

        if(c.getCount() < 1)
            return hasil;

        c.moveToFirst(); // Pasang di baris ke-1

        do {
            // Mengambil data dari kolom-kolom di tabel sesuai URUTANNYA.
            // Urutannya --> Db Helper saat kita membuat tabel kontak
            int id = c.getInt(0); // Kolom ke-0 INTEGER
            String waktu = c.getString(1); // Kolom ke-1 VARCHAR
            String makanan = c.getString(2); // Kolom ke-2 VARCHAR

            LogMakanEntity k = new LogMakanEntity();
            k.setId(id);
            k.setWaktu(waktu);
            k.setMakanan(makanan);

            // Setiap kontak yang didapat dimasukkan ke ArrayList hasil;
            hasil.add(k);
        }
        while(c.moveToNext());

        return hasil;
    }

    // untuk mencari kontak berdasarkan ID
    public LogMakanEntity cariBerdasarkanId(int id)
    {
        String sql = "SELECT * FROM kontak WHERE id = '" + id + "'";

        Cursor c = this.db.bacaData(sql);

        if(c.getCount() > 0)
        {
            c.moveToFirst();

            String waktu = c.getString(1);
            String makanan = c.getString(2);

            LogMakanEntity k = new LogMakanEntity();
            k.setId(id);
            k.setWaktu(waktu);
            k.setMakanan(makanan);

            return k;
        }

        return null;
    }
}
