package id.aryad.sipasar.repositories;

import java.text.DecimalFormat;
import java.util.Calendar;

public class NumberHelperRepository {
    public String asRpString(int uang) {
        DecimalFormat df = new DecimalFormat(",###,###");
        return "Rp. " + df.format(uang).replace(",", ".") + ", -";
    }

    public String asRpString(int uang, boolean addPrefix) {
        DecimalFormat df = new DecimalFormat(",###,###");
        String txt = df.format(uang).replace(",", ".") + " ,-";

        if (addPrefix) {
            return "Rp. " + txt;
        } else {
            return txt;
        }
    }

    private static NumberHelperRepository single_instance = null;

    public static NumberHelperRepository getInstance() {
        if (single_instance == null)
            single_instance = new NumberHelperRepository();

        return single_instance;
    }
}
