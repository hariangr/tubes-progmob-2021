package id.aryad.sipasar.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.aryad.sipasar.R;
import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.NumberHelperRepository;

public class HistoryGajiRecyclerView extends RecyclerView.Adapter<HistoryGajiRecyclerView.ViewHolder> {
    private HistoryGajiRecyclerViewCallback _callback;
    private ArrayList<HistoryGajiPegawai> _gajis;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView berlakuTV;
        private final TextView gajiTV;
        private final TextView statusTV;
        private final ImageButton deleteBtn;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            berlakuTV = (TextView) view.findViewById(R.id.row_history_gaji_berlaku);
            gajiTV = (TextView) view.findViewById(R.id.row_history_gaji_nilai);
            statusTV = (TextView) view.findViewById(R.id.row_history_gaji_status);
            deleteBtn = (ImageButton) view.findViewById(R.id.row_history_gaji_delete_btn);
        }

        public TextView getBerlakuTV() {
            return berlakuTV;
        }

        public TextView getGajiTV() {
            return gajiTV;
        }

        public TextView getStatusTV() {
            return statusTV;
        }

        public ImageButton getDeleteBtn() {
            return deleteBtn;
        }
    }

    public HistoryGajiRecyclerView(ArrayList<HistoryGajiPegawai> dataSet, HistoryGajiRecyclerViewCallback callback) {
        _gajis = dataSet;
        _callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_history_gaji, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        HistoryGajiPegawai gaji = _gajis.get(position);
        DecimalFormat df = new DecimalFormat(",###,###");

        viewHolder.getGajiTV().setText(NumberHelperRepository.getInstance().asRpString(gaji.getNilai_gaji()));
        if (gaji.getStatus() == 1) {
            viewHolder.getStatusTV().setText("Aktif");
        } else {
            viewHolder.getStatusTV().setText("Nonaktif");
        }
        viewHolder.getBerlakuTV().setText(gaji.getMulai_berlaku().toString());

        viewHolder.getDeleteBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _callback.onDeleteClicked(gaji, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _gajis.size();
    }
}
