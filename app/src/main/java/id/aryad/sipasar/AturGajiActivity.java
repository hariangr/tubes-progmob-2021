package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import id.aryad.sipasar.constants.IntentKey;
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
                Intent _intent = new Intent(getApplicationContext(),DetailHistoryGajiActivity.class);
                _intent.putExtra(IntentKey.ID_PEGAWAI_DETAIL_ACTIVITY, pegawai.getId_pegawai());
                startActivity(_intent);
                finish();
            }

            @Override
            public void onDeleteClicked(Pegawai pegawai, int position) {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        pegawaiRecyclerView.setLayoutManager(layoutManager);
        pegawaiRecyclerView.setAdapter(pegawaiAdapter);
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bayar_gaji_cmenu, menu);
//        return true;
//    }
}