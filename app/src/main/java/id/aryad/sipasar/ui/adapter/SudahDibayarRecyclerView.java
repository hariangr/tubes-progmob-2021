package id.aryad.sipasar.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.aryad.sipasar.R;
import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.models.PembayaranGaji;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.NumberHelperRepository;

public class SudahDibayarRecyclerView extends RecyclerView.Adapter<SudahDibayarRecyclerView.ViewHolder> {
    private BelumDibayarRecyclerViewCallback _callback;
    private List<PembayaranGaji> pembayaranGajis;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView sub;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.row_sudah_dibayar_nama);
            sub = (TextView) view.findViewById(R.id.row_sudah_dibayar_sub);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getSub() {
            return sub;
        }
    }

    public SudahDibayarRecyclerView(List<PembayaranGaji> dataSet, BelumDibayarRecyclerViewCallback callback) {
        pembayaranGajis = dataSet;
        _callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_sudah_dibayar, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        PembayaranGaji gaji = pembayaranGajis.get(position);

        viewHolder.getTitle().setText(gaji.getPegawai().getNama_pegawai());
        viewHolder.getSub().setText("Dibayarkan sejumlah " + NumberHelperRepository.getInstance().asRpString(gaji.getNilai_gaji()));
    }

    @Override
    public int getItemCount() {
        return pembayaranGajis.size();
    }
}
