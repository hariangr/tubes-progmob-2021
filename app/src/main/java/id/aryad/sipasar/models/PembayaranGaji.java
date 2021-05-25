package id.aryad.sipasar.models;

import java.util.Date;

public class PembayaranGaji {
    int id_pembayaran_gaji;
    int id_pegawai;
    int nilai_gaji;
    Date tanggal_bayar;

    public PembayaranGaji(int id_pembayaran_gaji, int id_pegawai, int nilai_gaji, Date tanggal_bayar) {
        this.id_pembayaran_gaji = id_pembayaran_gaji;
        this.id_pegawai = id_pegawai;
        this.nilai_gaji = nilai_gaji;
        this.tanggal_bayar = tanggal_bayar;
    }
}
