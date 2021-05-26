package id.aryad.sipasar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;

import id.aryad.sipasar.constants.MonthNames;
import id.aryad.sipasar.repositories.AuthRepository;
import id.aryad.sipasar.repositories.DateHelperRepository;
import id.aryad.sipasar.repositories.PembayaranGajiRepository;
import id.aryad.sipasar.ui.dialog.YearMonthDialog;
import id.aryad.sipasar.ui.main.SectionsPagerAdapter;

public class BayarGajiActivity extends AppCompatActivity {
    private int selectedMonth = DateHelperRepository.getInstance().getCurrentMonthNumber();
    private int selectedYear = DateHelperRepository.getInstance().getCurrentYear();

    private Button yearMonthIndicator;
    private Button aturGajiBtn;
    private Button logoutBtn;

    private MenuItem changeRange;
    private Menu menu;

    SectionsPagerAdapter sectionsPagerAdapter;

    public void updateYearMonthIndicator() {
        this.setTitle("Gaji " + MonthNames.Bahasa[selectedMonth] + " " + selectedYear);
        sectionsPagerAdapter.monthDateUpdated();
    }

    public void updatePaid() {
        sectionsPagerAdapter.updatePaid();
    }

    public int getSelectedMonth() {
        return selectedMonth;
    }

    public int getSelectedYear() {
        return selectedYear;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bayar_gaji_cmenu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_atur_gaji:
                Intent _intentAturGaji = new Intent(getApplicationContext(), AturGajiActivity.class);
                startActivity(_intentAturGaji);
                break;
            case R.id.menu_change_range:
                YearMonthDialog pd = new YearMonthDialog(selectedMonth, selectedYear, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedMonth = month;
                        selectedYear = year;
                        updateYearMonthIndicator();
                    }
                });
                pd.show(getSupportFragmentManager(), "Year Month Picker");
                break;
            case R.id.menu_logout:
                AuthRepository.getInstance().logout(getApplicationContext());
                Intent _intentAuth = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(_intentAuth);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_gaji);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        updateYearMonthIndicator();
        getSupportActionBar().setElevation(0);
    }
}