package id.aryad.sipasar.repositories;

import java.util.Calendar;
import java.util.Date;

import id.aryad.sipasar.constants.MonthNames;

public class DateHelperRepository {
    public int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public int getCurrentMonthNumber() {
        // 0 adalah januari, 11 adalah desember
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    public int getCurrentDateNumber() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }

    public Date getCurrentDate() {
        return new Date(getCurrentYear(), getCurrentMonthNumber(), getCurrentDateNumber());
    }

    public String getFriendlyDate(Date date) {
        String txt = String.valueOf(date.getDate());
        txt += " " + MonthNames.Bahasa[date.getMonth()];
        txt += " " + date.getYear();
        return txt;
    }

    private static DateHelperRepository single_instance = null;

    public static DateHelperRepository getInstance() {
        if (single_instance == null)
            single_instance = new DateHelperRepository();

        return single_instance;
    }
}
