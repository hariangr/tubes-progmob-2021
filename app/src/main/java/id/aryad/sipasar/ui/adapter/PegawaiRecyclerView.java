package id.aryad.sipasar.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.aryad.sipasar.R;
import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.NumberHelperRepository;

public class PegawaiRecyclerView extends RecyclerView.Adapter<PegawaiRecyclerView.ViewHolder> {
    private PegawaiRecyclerViewCallback _callback;
    private List<Pegawai> _pegawais;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTV;
        private final TextView gajiTV;
        private final ImageButton editBtn;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nameTV = (TextView) view.findViewById(R.id.row_pegawai_nilai);
            gajiTV = (TextView) view.findViewById(R.id.row_pegawai_berlaku);
            editBtn = (ImageButton) view.findViewById(R.id.row_pegawai_edit_btn);
        }

        public TextView getGajiTV() {
            return gajiTV;
        }

        public TextView getNameTV() {
            return nameTV;
        }

        public ImageButton getEditBtn() {
            return editBtn;
        }
    }

    public PegawaiRecyclerView(List<Pegawai> dataSet, PegawaiRecyclerViewCallback callback) {
        _pegawais = dataSet;
        _callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_pegawai, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Pegawai pegawai = _pegawais.get(position);
        viewHolder.getNameTV().setText(pegawai.getNama_pegawai());

        HistoryGajiPegawai currentGaji = HistoryGajiRepository.getInstance().getCurrentHistoryGajiByPegawaiId(pegawai.getId_pegawai());
        if (currentGaji != null) {
            viewHolder.getGajiTV().setText(NumberHelperRepository.getInstance().asRpString(currentGaji.getNilai_gaji()));
        } else {
            viewHolder.getGajiTV().setText("Tidak ada periode gaji aktif");
        }

        viewHolder.getEditBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pegawai it = _pegawais.get(position);
                _callback.onEditClicked(it, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _pegawais.size();
    }
}
