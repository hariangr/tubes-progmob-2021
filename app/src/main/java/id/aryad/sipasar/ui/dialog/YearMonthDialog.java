package id.aryad.sipasar.ui.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import java.util.Calendar;

import id.aryad.sipasar.R;
import id.aryad.sipasar.constants.MonthNames;
import id.aryad.sipasar.repositories.DateHelperRepository;

public class YearMonthDialog extends DialogFragment {
    private int _selectedMonth;
    private int _selectedYear;

    private static final int MAX_YEAR = 2099;
    private static final int MIN_YEAR = 2010;
    private DatePickerDialog.OnDateSetListener listener;

    public YearMonthDialog(int selectedMonth, int selectedYear, DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
        this._selectedMonth = selectedMonth;
        this._selectedYear = selectedYear;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.dialog_year_month, null);

        final NumberPicker monthPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(11);
        monthPicker.setDisplayedValues(MonthNames.Bahasa);

        yearPicker.setMinValue(MIN_YEAR);
        yearPicker.setMaxValue(MAX_YEAR);

        monthPicker.setValue(_selectedMonth - 1); // Bulan dimulai dari 1, sedangkan index dimulai dari 0
        yearPicker.setValue(_selectedYear);

        builder.setView(dialog)
                .setPositiveButton("Pilih", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue() + 1, 0);
                    }
                })
                .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        YearMonthDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}