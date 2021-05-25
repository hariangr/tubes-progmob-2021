package id.aryad.sipasar.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import id.aryad.sipasar.constants.PrefKey;
import id.aryad.sipasar.models.Admin;
import id.aryad.sipasar.models.AdminRole;
import id.aryad.sipasar.models.Pegawai;

public class PegawaiRepository {
    private ArrayList<Pegawai> pegawaiDatasets = new ArrayList(Arrays.asList(
            new Pegawai(0, "Arya as Pegawai", "Jln Something", ""),
            new Pegawai(1, "Arya as Admin", "Jln Something", ""),
            new Pegawai(2, "Arya as Manager", "Jln Something", "")
    ));

    public ArrayList<Pegawai> getAll() {
        return pegawaiDatasets;
    }

    public Pegawai byId(int id) {
        for (Pegawai it : pegawaiDatasets) {
            if (it.getId_pegawai() == id) {
                return it;
            }
        }

        return null;
    }

    public void deleteById(int id) {
        for (int i = 0; i < pegawaiDatasets.size(); i++) {
            Pegawai it = pegawaiDatasets.get(i);
            if (it.getId_pegawai() == id) {
                pegawaiDatasets.remove(i);
            }
        }
    }

    public Pegawai create(String nama_pegawai, String alamat, String foto) {
        Pegawai _new = new Pegawai(pegawaiDatasets.size(), nama_pegawai, alamat, foto);
        return _new;
    }

    private static PegawaiRepository single_instance = null;

    public static PegawaiRepository getInstance() {
        if (single_instance == null)
            single_instance = new PegawaiRepository();

        return single_instance;
    }
}
