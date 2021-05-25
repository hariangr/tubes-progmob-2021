package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import id.aryad.sipasar.constants.IntentKey;
import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.NumberHelperRepository;
import id.aryad.sipasar.repositories.PegawaiRepository;
import id.aryad.sipasar.ui.adapter.HistoryGajiRecyclerView;
import id.aryad.sipasar.ui.adapter.HistoryGajiRecyclerViewCallback;
import id.aryad.sipasar.ui.adapter.PegawaiRecyclerView;
import id.aryad.sipasar.ui.adapter.PegawaiRecyclerViewCallback;

public class DetailHistoryGajiActivity extends AppCompatActivity {
    private TextInputLayout namaPegawaiET;
    private TextInputLayout currentGajiET;

    private RecyclerView hgajiRecyclerView;
    private HistoryGajiRecyclerView hgajiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_gaji);

        namaPegawaiET = (TextInputLayout) findViewById(R.id.detail_activity_nama_pegawai);
        currentGajiET = (TextInputLayout) findViewById(R.id.detail_activity_current_gaji_pegawai);


        int pegawai_id_intent = getIntent().getIntExtra(IntentKey.ID_PEGAWAI_DETAIL_ACTIVITY, -1);
        if (pegawai_id_intent == -1) {
            throw new Error("Harus ada id yang di pass");
        }

        Pegawai pegawai = PegawaiRepository.getInstance().byId(pegawai_id_intent);

        namaPegawaiET.getEditText().setText(pegawai.getNama_pegawai());

        HistoryGajiPegawai gajiAktif = HistoryGajiRepository.getInstance().getCurrentHistoryGajiByPegawaiId(pegawai.getId_pegawai());
        if (gajiAktif == null ) {
            currentGajiET.getEditText().setText("Tidak ada gaji aktif");
        } else {
            int nilaiGajiAktif = gajiAktif.getNilai_gaji();
            currentGajiET.getEditText().setText(NumberHelperRepository.getInstance().asRpString(nilaiGajiAktif, false));
        }

        hgajiRecyclerView = (RecyclerView) findViewById(R.id.activity_detail_history_gaji_recycler_view);
        hgajiAdapter = new HistoryGajiRecyclerView(HistoryGajiRepository.getInstance().getHistoryGajiByPegawaiId(pegawai_id_intent), new HistoryGajiRecyclerViewCallback() {
            @Override
            public void onEditClicked(HistoryGajiPegawai gaji, int position) {

            }

            @Override
            public void onDeleteClicked(HistoryGajiPegawai gaji, int position) {
                HistoryGajiRepository.getInstance().markInactiveById(gaji.getId_history_gaji_pegawai());
                hgajiAdapter.notifyDataSetChanged();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        hgajiRecyclerView.setLayoutManager(layoutManager);
        hgajiRecyclerView.setAdapter(hgajiAdapter);

        getSupportActionBar().setElevation(0);
    }
}