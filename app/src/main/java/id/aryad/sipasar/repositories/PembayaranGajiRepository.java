package id.aryad.sipasar.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import id.aryad.sipasar.constants.PrefKey;
import id.aryad.sipasar.models.Admin;
import id.aryad.sipasar.models.AdminRole;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.models.PembayaranGaji;

public class PembayaranGajiRepository {
    private ArrayList<PembayaranGaji> pembayaranDatasets = new ArrayList(Arrays.asList(
            new PembayaranGaji(0, 0, 2500000, new Date(2021, 4, 20))
    ));

    public PembayaranGaji pegawaiPaidByMonthYear(int pegawaiId, int month, int year) {
        for (PembayaranGaji it : pembayaranDatasets) {
            if (it.getId_pegawai() == pegawaiId && it.getTanggal_bayar().getMonth() == month && it.getTanggal_bayar().getYear() == year) {
                return it;
            }
        }

        return null;
    }

    public List<Pegawai> unpaidByMonthYear(int month, int year) {
        List<Pegawai> _res = new ArrayList<>();

        for (Pegawai it : PegawaiRepository.getInstance().getAll()) {
            if (pegawaiPaidByMonthYear(it.getId_pegawai(), month, year) == null) {
                _res.add(it);
            }
        }

        return _res;
    }

    public void pay(int id_pegawai, Date date, int gaji) {
        PembayaranGaji _pembayaran = new PembayaranGaji(pembayaranDatasets.size(), id_pegawai, gaji, date);
        pembayaranDatasets.add(_pembayaran);
    }

    public List<Pegawai> paidByMonthYear(int month, int year) {
        List<Pegawai> _res = new ArrayList<>();

        for (Pegawai it : PegawaiRepository.getInstance().getAll()) {
            if (pegawaiPaidByMonthYear(it.getId_pegawai(), month, year) != null) {
                _res.add(it);
            }
        }

        return _res;
    }

    private static PembayaranGajiRepository single_instance = null;

    public static PembayaranGajiRepository getInstance() {
        if (single_instance == null)
            single_instance = new PembayaranGajiRepository();

        return single_instance;
    }
}
