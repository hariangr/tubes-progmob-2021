package id.aryad.sipasar.models;

public class Pegawai {
    int Id_pegawai;
    String Nama_pegawai;
    String Alamat;
    String Foto;

    public Pegawai(int id_pegawai, String nama_pegawai, String alamat, String foto) {
        Id_pegawai = id_pegawai;
        Nama_pegawai = nama_pegawai;
        Alamat = alamat;
        Foto = foto;
    }

    public int getId_pegawai() {
        return Id_pegawai;
    }

    public String getNama_pegawai() {
        return Nama_pegawai;
    }

    public String getAlamat() {
        return Alamat;
    }

    public String getFoto() {
        return Foto;
    }
}
