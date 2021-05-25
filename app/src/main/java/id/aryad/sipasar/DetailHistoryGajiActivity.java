package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import id.aryad.sipasar.constants.IntentKey;
import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.PegawaiRepository;

public class DetailHistoryGajiActivity extends AppCompatActivity {
    private TextInputLayout namaPegawaiET;
    private TextInputLayout currentGajiET;

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
            currentGajiET.getEditText().setText(String.valueOf(nilaiGajiAktif));
        }
    }
}