package id.aryad.sipasar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import id.aryad.sipasar.constants.IntentKey;
import id.aryad.sipasar.repositories.AuthRepository;
import id.aryad.sipasar.ui.main.SectionsPagerAdapter;

public class BayarGajiActivity extends AppCompatActivity {
    int selectedMonth = Calendar.getInstance().get(Calendar.MONTH);
    int selectedYear = Calendar.getInstance().get(Calendar.YEAR);

    TextView activityTitle;
    Button btnSelectRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_gaji);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        activityTitle = (TextView) findViewById(R.id.title);
        updateSelectedMonth();

        btnSelectRange = (Button) findViewById(R.id.btnSelectRange);
        btnSelectRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = createDialogWithoutDateField();
                dialog.show();
            }
        });
    }

    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, 2014, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
//                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
        return dpd;
    }

    void updateSelectedMonth() {
        String month_name = (String)android.text.format.DateFormat.format("MMMM", new Date());
//        int id_admin = getIntent().getIntExtra(IntentKey.CURRENT_ADMIN_ID, -1);
//        String username;
//        if (id_admin != -1) {
//            username = AuthRepository.getInstance().getAdminById(id_admin).getUsername();
//        } else {
//            username = "Gagal Login";
//        }
        String username = AuthRepository.getInstance().getCurrentAdmin().getUsername();

        activityTitle.setText("Gaji " + month_name + ", " + String.valueOf(selectedYear) + " (" + username + ")");
    }
}