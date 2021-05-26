package id.aryad.sipasar.ui.adapter;

import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;

public interface BelumDibayarRecyclerViewCallback {
    void onPayClicked(Pegawai pegawai, int position, boolean isPayable);
}
