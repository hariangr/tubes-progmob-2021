package id.aryad.sipasar;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import id.aryad.sipasar.constants.MonthNames;
import id.aryad.sipasar.repositories.DateHelperRepository;
import id.aryad.sipasar.ui.dialog.YearMonthDialog;
import id.aryad.sipasar.ui.main.SectionsPagerAdapter;

public class BayarGajiActivity extends AppCompatActivity {
    private int selectedMonth = DateHelperRepository.getInstance().getCurrentMonthNumber();
    private int selectedYear = DateHelperRepository.getInstance().getCurrentYear();

    private Button yearMonthIndicator;

    public void updateYearMonthIndicator() {
        yearMonthIndicator.setText(MonthNames.Bahasa[selectedMonth - 1] + " " + selectedYear);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_gaji);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        yearMonthIndicator = (Button) findViewById(R.id.yearMonthIndicator);

        yearMonthIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YearMonthDialog pd = new YearMonthDialog(selectedMonth, selectedYear, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Log.v("Entah", String.valueOf(year) + " " + String.valueOf(month) + " " + String.valueOf(dayOfMonth));
                        selectedMonth = month;
                        selectedYear = year;
                        updateYearMonthIndicator();
                    }
                });

                pd.show(getSupportFragmentManager(), "Year Month Picker");
            }
        });


        updateYearMonthIndicator();
    }
}