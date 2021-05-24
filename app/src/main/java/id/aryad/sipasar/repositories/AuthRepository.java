package id.aryad.sipasar.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import id.aryad.sipasar.constants.PrefKey;
import id.aryad.sipasar.models.Admin;
import id.aryad.sipasar.models.AdminRole;

public class AuthRepository {
    private ArrayList<Admin> adminDatasets = new ArrayList(Arrays.asList(
            new Admin(0, 0, "aryapegawai", "pass123", AdminRole.PEGAWAI, 1),
            new Admin(1, 1, "aryaadmin", "pass123", AdminRole.ADMIN, 1),
            new Admin(2, 2, "aryamanager", "pass123", AdminRole.MANAGER, 1)
    ));

    public Admin getCurrentAdmin(Context context) {
        Log.v("Apalah", "something");
        SharedPreferences _pref = PreferenceManager.getDefaultSharedPreferences(context);
        int _adminid = _pref.getInt(PrefKey.CUR_USER_ID, -1);

        if (_adminid == -1) {
            return null;
        }

        Log.v("Apalah", String.valueOf(_adminid));
        Admin _admin = getAdminById(_adminid);
        return _admin;
    }

    public void register(String username, String password) {
//        adminDatasets.add(new Admin());
    }

    public Admin login(Context context, String username, String password) {
        SharedPreferences _pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor _editor = _pref.edit();

        for (Admin it : adminDatasets) {
            if (it.getUsername().equalsIgnoreCase(username) && it.getPassword().equals(password)) {
                _editor.putInt(PrefKey.CUR_USER_ID, it.getId_admin());
                _editor.apply();
                Log.v("Apalah", "ditambahkan");
                return it;
            }
        }

        return null;
    }

    public void logout(Context context) {
        SharedPreferences _pref = PreferenceManager.getDefaultSharedPreferences(context);
        _pref.edit().remove(PrefKey.CUR_USER_ID).apply();
    }

    public Admin getAdminById(int id) {
        for (Admin it : adminDatasets) {
            if (it.getId_admin() == id) {
                return it;
            }
        }

        return null;
    }


    private static AuthRepository single_instance = null;

    public static AuthRepository getInstance() {
        if (single_instance == null)
            single_instance = new AuthRepository();

        return single_instance;
    }
}
