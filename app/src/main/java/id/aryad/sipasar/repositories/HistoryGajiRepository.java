package id.aryad.sipasar.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;

public class HistoryGajiRepository {
    private ArrayList<HistoryGajiPegawai> historyGaji = new ArrayList(Arrays.asList(
            new HistoryGajiPegawai(0, 0, 3000000, new Date(2021, 5, 1), new Date(2021, 5, 4), 0),
            new HistoryGajiPegawai(1, 0, 3500000, new Date(2021, 5, 5), null, 1),
            new HistoryGajiPegawai(2, 1, 2500000, new Date(2021, 5, 1), null, 1),
            new HistoryGajiPegawai(3, 2, 2200000, new Date(2021, 5, 1), null, 1)
    ));

    public ArrayList<HistoryGajiPegawai> getAll() {
        return historyGaji;
    }

    public  ArrayList<HistoryGajiPegawai> getHistoryGajiByPegawaiId(int pegawai_id) {
        ArrayList<HistoryGajiPegawai> temp = new ArrayList<HistoryGajiPegawai>();
        for (HistoryGajiPegawai it : historyGaji) {
            if (it.getId_pegawai() == pegawai_id) {
                temp.add(it);
            }
        }
        return temp;
    }

    public HistoryGajiPegawai byId(int id) {
        for (HistoryGajiPegawai it : historyGaji) {
            if (it.getId_history_gaji_pegawai() == id) {
                return it;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        for (int i = 0; i < historyGaji.size(); i++) {
            HistoryGajiPegawai it = historyGaji.get(i);
            if (it.getId_history_gaji_pegawai() == id) {
                historyGaji.remove(i);
            }
        }
    }

    public HistoryGajiPegawai create(int id_pegawai, int nilai_gaji, Date mulai_berlaku, Date
            selesai_berlaku, int status) {
        HistoryGajiPegawai _new = new HistoryGajiPegawai(historyGaji.size(), id_pegawai, nilai_gaji, mulai_berlaku, selesai_berlaku, status);
        return _new;
    }

    private static HistoryGajiRepository single_instance = null;

    public static HistoryGajiRepository getInstance() {
        if (single_instance == null)
            single_instance = new HistoryGajiRepository();

        return single_instance;
    }
}
