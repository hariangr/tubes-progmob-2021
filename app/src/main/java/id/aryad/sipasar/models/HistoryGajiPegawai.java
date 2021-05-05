package id.aryad.sipasar.models;

import java.time.LocalDateTime;

public class HistoryGajiPegawai {
    int Id_history_gaji_pegawai;
    int Id_pegawai;
    int Nilai_gaji;
    LocalDateTime Mulai_berlaku;
    LocalDateTime Selesai;
    int Status;

    public HistoryGajiPegawai(int id_history_gaji_pegawai, int id_pegawai, int nilai_gaji, LocalDateTime mulai_berlaku, LocalDateTime selesai, int status) {
        Id_history_gaji_pegawai = id_history_gaji_pegawai;
        Id_pegawai = id_pegawai;
        Nilai_gaji = nilai_gaji;
        Mulai_berlaku = mulai_berlaku;
        Selesai = selesai;
        Status = status;
    }
}
