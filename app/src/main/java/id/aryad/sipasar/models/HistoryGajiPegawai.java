package id.aryad.sipasar.models;

import java.util.Date;

import id.aryad.sipasar.repositories.DateHelperRepository;

public class HistoryGajiPegawai {
    int Id_history_gaji_pegawai;
    int Id_pegawai;
    int Nilai_gaji;
    Date Mulai_berlaku;
    Date Selesai;
    int Status; // 1 aktif; 0 nonaktif

    public int getId_history_gaji_pegawai() {
        return Id_history_gaji_pegawai;
    }

    public int getId_pegawai() {
        return Id_pegawai;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public void setSelesai(Date selesai) {
        Selesai = selesai;
    }

    public String getDateRangeFriendly() {
        if (getSelesai() == null) {
            return DateHelperRepository.getInstance().getFriendlyDate(getMulai_berlaku());
        } else {
            return DateHelperRepository.getInstance().getFriendlyDate(getMulai_berlaku()) + " - " + DateHelperRepository.getInstance().getFriendlyDate(getSelesai());
        }
    }

    public int getNilai_gaji() {
        return Nilai_gaji;
    }

    public Date getMulai_berlaku() {
        return Mulai_berlaku;
    }

    public Date getSelesai() {
        return Selesai;
    }

    public int getStatus() {
        return Status;
    }

    public HistoryGajiPegawai(int id_history_gaji_pegawai, int id_pegawai, int nilai_gaji, Date mulai_berlaku, Date selesai, int status) {
        Id_history_gaji_pegawai = id_history_gaji_pegawai;
        Id_pegawai = id_pegawai;
        Nilai_gaji = nilai_gaji;
        Mulai_berlaku = mulai_berlaku;
        Selesai = selesai;
        Status = status;
    }
}
