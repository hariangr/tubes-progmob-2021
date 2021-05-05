package id.aryad.sipasar.repositories;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import id.aryad.sipasar.models.Admin;
import id.aryad.sipasar.models.AdminRole;

public class AuthRepository {
    private ArrayList<Admin> adminDatasets = new ArrayList(Arrays.asList(
            new Admin(0, 0, "hariangr", "password", AdminRole.ADMIN, 1),
            new Admin(1, 0, "something", "password", AdminRole.MANAGER, 1)
    ));

    public Admin login(String username, String password) {
        for (Admin it : adminDatasets) {
            if (it.getUsername().equalsIgnoreCase(username) && it.getPassword().equals(password)) {
                return it;
            }
        }

        return null;
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
