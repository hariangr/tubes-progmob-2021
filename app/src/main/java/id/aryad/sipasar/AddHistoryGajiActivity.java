package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import id.aryad.sipasar.constants.IntentKey;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.PegawaiRepository;

public class AddHistoryGajiActivity extends AppCompatActivity {
    Pegawai pegawai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history_gaji);

        int pegawai_intent_id = getIntent().getIntExtra(IntentKey.ID_PEGAWAI_ADD_HISTORY_GAJI_ACTIVITY, -1);
        if (pegawai_intent_id == -1) {
            throw new Error("There should be id");
        }

        pegawai = PegawaiRepository.getInstance().byId(pegawai_intent_id);
    }

    @Override
    public void onBackPressed() {
        Intent _intent = new Intent(getApplicationContext(),DetailHistoryGajiActivity.class);
        _intent.putExtra(IntentKey.ID_PEGAWAI_DETAIL_ACTIVITY, pegawai.getId_pegawai());
        startActivity(_intent);
        finish();
//        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
}