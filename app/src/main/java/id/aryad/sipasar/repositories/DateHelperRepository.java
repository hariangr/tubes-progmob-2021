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
        // 1 adalah januari, 12 adalah desember
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
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
