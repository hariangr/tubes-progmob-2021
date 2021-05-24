package id.aryad.sipasar.repositories;

import java.util.Calendar;

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

    private static DateHelperRepository single_instance = null;

    public static DateHelperRepository getInstance() {
        if (single_instance == null)
            single_instance = new DateHelperRepository();

        return single_instance;
    }
}
