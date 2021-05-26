package id.aryad.sipasar.ui.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import id.aryad.sipasar.R;
import id.aryad.sipasar.constants.MonthNames;
import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.DateHelperRepository;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.NumberHelperRepository;

public class DoPayDialog extends DialogFragment {
    Pegawai pegawai;
    int month;
    int year;
    DoPayCallback callback;

    public DoPayDialog(Pegawai pegawai, int selectedMonth, int selectedYear, DoPayCallback callback) {
        this.pegawai = pegawai;
        this.month = selectedMonth;
        this.year = selectedYear;
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.dialog_do_pay, null);

        final TextInputLayout nama = (TextInputLayout) dialog.findViewById(R.id.dopay_nama);
        final TextInputLayout bulantahun = (TextInputLayout) dialog.findViewById(R.id.dopay_bulantahun);
        final TextInputLayout jumlah = (TextInputLayout) dialog.findViewById(R.id.dopay_jumlah);

        nama.getEditText().setText(pegawai.getNama_pegawai());
        bulantahun.getEditText().setText(DateHelperRepository.getInstance().getFriendlyDate(new Date(year, month, 1)));

        HistoryGajiPegawai __gaji = HistoryGajiRepository.getInstance().getCurrentHistoryGajiByPegawaiId(pegawai.getId_pegawai());

        if (__gaji == null) {
            Toast t = Toast.makeText(getActivity().getApplicationContext(), "Silahkan atur gaji", Toast.LENGTH_SHORT);
            t.show();
        }
        int __jumlah = __gaji.getNilai_gaji();
        jumlah.getEditText().setText(NumberHelperRepository.getInstance().asRpString(__jumlah));

        builder.setView(dialog)
                .setPositiveButton("Pilih", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        callback.okClicked();
                    }
                })
                .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        callback.cancelClicked();
                    }
                });
        return builder.create();
    }
}