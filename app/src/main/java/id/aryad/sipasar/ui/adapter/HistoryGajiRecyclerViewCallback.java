package id.aryad.sipasar.ui.adapter;

import id.aryad.sipasar.models.HistoryGajiPegawai;

public interface HistoryGajiRecyclerViewCallback {
    void onEditClicked(HistoryGajiPegawai gaji, int position);

    void onDeleteClicked(HistoryGajiPegawai gaji, int position);
}
