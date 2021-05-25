package id.aryad.sipasar.ui.adapter;

import id.aryad.sipasar.models.Pegawai;

public interface PegawaiRecyclerViewCallback {
    void onEditClicked(Pegawai pegawai, int position);

    void onDeleteClicked(Pegawai pegawai, int position);
}
