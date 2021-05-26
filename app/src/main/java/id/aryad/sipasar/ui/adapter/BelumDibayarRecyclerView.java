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
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.NumberHelperRepository;

public class BelumDibayarRecyclerView extends RecyclerView.Adapter<BelumDibayarRecyclerView.ViewHolder> {
    private BelumDibayarRecyclerViewCallback _callback;
    private List<Pegawai> _pegawais;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView sub;
        private final Button btn;
        private boolean isPayable;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.row_sudah_dibayar_nama);
            sub = (TextView) view.findViewById(R.id.row_sudah_dibayar_sub);
            btn = (Button) view.findViewById(R.id.row_belum_dibayar_btn_bayar);
        }


        public boolean isPayable() {
            return isPayable;
        }

        public TextView getTitle() {
            return title;
        }

        public void setPayable(boolean payable) {
            isPayable = payable;
        }

        public TextView getSub() {
            return sub;
        }

        public Button getBtn() {
            return btn;
        }
    }

    public BelumDibayarRecyclerView(List<Pegawai> dataSet, BelumDibayarRecyclerViewCallback callback) {
        _pegawais = dataSet;
        _callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_belum_dibayar, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Pegawai pegawai = _pegawais.get(position);

        boolean isPayable;

        HistoryGajiPegawai currentGaji = HistoryGajiRepository.getInstance().getCurrentHistoryGajiByPegawaiId(pegawai.getId_pegawai());
        viewHolder.getTitle().setText(pegawai.getNama_pegawai());
        if (currentGaji != null) {
            viewHolder.getSub().setText(NumberHelperRepository.getInstance().asRpString(currentGaji.getNilai_gaji()));
            isPayable = true;
            viewHolder.getBtn().setText("Bayar");
        } else {
            isPayable = false;
            viewHolder.getSub().setText("Tidak ada periode gaji aktif");
            viewHolder.setPayable(false);
            viewHolder.getBtn().setText("Atur Gaji");
        }

        viewHolder.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pegawai it = _pegawais.get(position);
                _callback.onPayClicked(it, position, isPayable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _pegawais.size();
    }
}
