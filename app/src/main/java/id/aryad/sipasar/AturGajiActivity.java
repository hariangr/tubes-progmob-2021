package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.PegawaiRepository;
import id.aryad.sipasar.ui.adapter.PegawaiRecyclerView;
import id.aryad.sipasar.ui.adapter.PegawaiRecyclerViewCallback;

public class AturGajiActivity extends AppCompatActivity {

    private RecyclerView pegawaiRecyclerView;
    private PegawaiRecyclerView pegawaiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_gaji);

        pegawaiRecyclerView = (RecyclerView) findViewById(R.id.atur_gaji_recyclerview);

        pegawaiAdapter = new PegawaiRecyclerView(PegawaiRepository.getInstance().getAll(), new PegawaiRecyclerViewCallback() {
            @Override
            public void onEditClicked(Pegawai pegawai, int position) {
                PegawaiRepository.getInstance().deleteById(pegawai.getId_pegawai());
                pegawaiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDeleteClicked(Pegawai pegawai, int position) {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        pegawaiRecyclerView.setLayoutManager(layoutManager);
        pegawaiRecyclerView.setAdapter(pegawaiAdapter);
    }
}