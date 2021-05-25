package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import id.aryad.sipasar.constants.IntentKey;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.DateHelperRepository;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.PegawaiRepository;

public class AddHistoryGajiActivity extends AppCompatActivity {
    private Pegawai pegawai;
    private TextInputLayout etNama;
    private TextInputLayout etJumlah;
    private TextInputLayout etTanggalMulai;

    private Button btnBatal;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history_gaji);

        int pegawai_intent_id = getIntent().getIntExtra(IntentKey.ID_PEGAWAI_ADD_HISTORY_GAJI_ACTIVITY, -1);
        if (pegawai_intent_id == -1) {
            throw new Error("There should be id");
        }

        pegawai = PegawaiRepository.getInstance().byId(pegawai_intent_id);

        etNama = (TextInputLayout) findViewById(R.id.nama_pegawai_et);
        etJumlah = (TextInputLayout) findViewById(R.id.jumlah_gaji_et);
        etTanggalMulai = (TextInputLayout) findViewById(R.id.tgl_mulai_et);

        etTanggalMulai.getEditText().setText(DateHelperRepository.getInstance().getFriendlyDate(new Date()));
        etNama.getEditText().setText(pegawai.getNama_pegawai());

        btnSimpan = (Button) findViewById(R.id.btn_simpan);
        btnBatal = (Button) findViewById(R.id.btn_batal);

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etJumlah.getEditText().getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Jumlah gaji tidak valid", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                int jumlahGaji = Integer.parseInt(etJumlah.getEditText().getText().toString());
                if (jumlahGaji < 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Jumlah gaji tidak boleh negatif", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                HistoryGajiRepository.getInstance().markAllInactiveByPegawaiId(pegawai.getId_pegawai());
                HistoryGajiRepository.getInstance().create(pegawai.getId_pegawai(), jumlahGaji, DateHelperRepository.getInstance().getCurrentDate(), null, 1);

                Toast toast = Toast.makeText(getApplicationContext(), "Berhasil menambahkan periode gaji baru", Toast.LENGTH_SHORT);
                toast.show();

                Intent _intent = new Intent(getApplicationContext(), DetailHistoryGajiActivity.class);
                _intent.putExtra(IntentKey.ID_PEGAWAI_DETAIL_ACTIVITY, pegawai.getId_pegawai());
                startActivity(_intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent _intent = new Intent(getApplicationContext(), DetailHistoryGajiActivity.class);
        _intent.putExtra(IntentKey.ID_PEGAWAI_DETAIL_ACTIVITY, pegawai.getId_pegawai());
        startActivity(_intent);
        finish();
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