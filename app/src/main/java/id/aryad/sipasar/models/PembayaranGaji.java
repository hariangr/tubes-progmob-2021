package id.aryad.sipasar.models;

import java.util.Date;

import id.aryad.sipasar.repositories.PegawaiRepository;

public class PembayaranGaji {
    int id_pembayaran_gaji;
    int id_pegawai;
    int nilai_gaji;
    Date tanggal_bayar;

    public int getId_pembayaran_gaji() {
        return id_pembayaran_gaji;
    }

    public Pegawai getPegawai() {
        return PegawaiRepository.getInstance().byId(id_pegawai);
    }

    public int getId_pegawai() {
        return id_pegawai;
    }

    public int getNilai_gaji() {
        return nilai_gaji;
    }

    public Date getTanggal_bayar() {
        return tanggal_bayar;
    }

    public PembayaranGaji(int id_pembayaran_gaji, int id_pegawai, int nilai_gaji, Date tanggal_bayar) {
        this.id_pembayaran_gaji = id_pembayaran_gaji;
        this.id_pegawai = id_pegawai;
        this.nilai_gaji = nilai_gaji;
        this.tanggal_bayar = tanggal_bayar;
    }
}
