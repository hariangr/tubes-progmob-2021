package id.aryad.sipasar.models;

import java.util.Date;

public class HistoryGajiPegawai {
    int Id_history_gaji_pegawai;
    int Id_pegawai;
    int Nilai_gaji;
    Date Mulai_berlaku;
    Date Selesai;
    int Status; // 1 aktif; 0 nonaktif

    public HistoryGajiPegawai(int id_history_gaji_pegawai, int id_pegawai, int nilai_gaji, Date mulai_berlaku, Date selesai, int status) {
        Id_history_gaji_pegawai = id_history_gaji_pegawai;
        Id_pegawai = id_pegawai;
        Nilai_gaji = nilai_gaji;
        Mulai_berlaku = mulai_berlaku;
        Selesai = selesai;
        Status = status;
    }
}
