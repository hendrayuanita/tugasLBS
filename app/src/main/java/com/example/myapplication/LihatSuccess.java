package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.entity.LogMakanEntity;
import com.example.myapplication.model.MakanModel;

import java.util.ArrayList;

public class LihatSuccess extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    // Jadikan properti kelas
    private ListView lstDaftarMenu;

    // Adapter untuk ListView
    private ArrayAdapter<String> adapterDaftarMenu;

    // Daftar nama dalam bentuk ArrayList<String>
    private ArrayList<String> daftarWaktu; // Contoh: ['Adi', 'Budi', 'Cici']

    // Daftar kontak dari database
    private ArrayList<LogMakanEntity> daftarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_success);

        this.lstDaftarMenu = this.findViewById(R.id.lst_daftar_kontak);

        this.daftarWaktu = new ArrayList<>(); // [], Array yang belum punya isi, tetapi TIDAK null.

        this.isiDaftarWaktu();

        this.adapterDaftarMenu = new ArrayAdapter<>(
                this, // Konteks/Activity yang memanggil
                android.R.layout.simple_list_item_1, // Template bawaan Android
                this.daftarWaktu // Dari mana datanya diambil
        );

        // Pasangkan adapter ke ListView
        this.lstDaftarMenu.setAdapter(this.adapterDaftarMenu);

        // Ketika list item diklik muncul toast.
        // Event Listener
        // Tentang: objek yang "mendengarkan" apa yang dialami oleh objek yang didengarkan
        this.lstDaftarMenu.setOnItemClickListener(this);
    }

    private void isiDaftarWaktu() {
        MakanModel mMakan = new MakanModel(this);

        ArrayList<LogMakanEntity> daftarMakanan = mMakan.ambilSemuaKontak();

        for (LogMakanEntity k : daftarMenu)
        {
            this.daftarWaktu.add(k.getWaktu());
        }

        // Kita simpan daftarKontaknya ke property class
        this.daftarMenu = daftarMenu;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        // Lakukan apa saja yang Anda inginkan ketika itemnya ListView diklik.
        // Ambil kontak dari array sesuai dengan posisi klik oleh user
        LogMakanEntity kontakDiKlik = this.daftarMenu.get(position);

        int kontakId = kontakDiKlik.getId();

        Intent i = new Intent(LihatSuccess.this, Success.class);
        i.putExtra("kontakId", kontakId);
        this.startActivity(i);
    }

    public void btnRefresh_onClick(View view) {
        this.daftarMenu.clear();

        this.daftarWaktu.clear();

        this.adapterDaftarMenu.clear();

        this.isiDaftarWaktu();

        this.adapterDaftarMenu.notifyDataSetChanged();

        Toast.makeText(this, "Data telah di-refresh..", Toast.LENGTH_SHORT).show();
    }


    public void btnKembali_onClick(View view) {
        this.finish();
    }
}