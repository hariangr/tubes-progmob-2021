package id.aryad.sipasar.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import id.aryad.sipasar.BayarGajiActivity;
import id.aryad.sipasar.DetailHistoryGajiActivity;
import id.aryad.sipasar.R;
import id.aryad.sipasar.constants.IntentKey;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.models.PembayaranGaji;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.PembayaranGajiRepository;
import id.aryad.sipasar.ui.adapter.BelumDibayarRecyclerView;
import id.aryad.sipasar.ui.adapter.BelumDibayarRecyclerViewCallback;
import id.aryad.sipasar.ui.adapter.SudahDibayarRecyclerView;
import id.aryad.sipasar.ui.dialog.DoPayCallback;
import id.aryad.sipasar.ui.dialog.DoPayDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TerbayarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TerbayarFragment extends Fragment {
    private RecyclerView terbayarRV;
    private SudahDibayarRecyclerView adapter;
    private TextView emptyNotice;

    List<PembayaranGaji> terbayars;

    public TerbayarFragment() {
        // Required empty public constructor
    }

    public static TerbayarFragment newInstance() {
        TerbayarFragment fragment = new TerbayarFragment();
        return fragment;
    }

    private void updateEmptyIndicator() {
        if (terbayars.size() == 0) {
            emptyNotice.setVisibility(View.VISIBLE);
        } else {
            emptyNotice.setVisibility(View.INVISIBLE);
        }
    }

    public void forceUpdate() {
        int month = ((BayarGajiActivity) getActivity()).getSelectedMonth();
        int year = ((BayarGajiActivity) getActivity()).getSelectedYear();

        terbayars = PembayaranGajiRepository.getInstance().getPembayaranGajiPaidByMonthYear(month,year);
        createAdapter();
        terbayarRV.setAdapter(adapter);
        terbayarRV.invalidate();
        updateEmptyIndicator();
    }

    public void monthYearUpdated() {
        try {
            forceUpdate();
        } catch (Exception err) {
            // pass
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void createAdapter() {
        int month = ((BayarGajiActivity) getActivity()).getSelectedMonth();
        int year = ((BayarGajiActivity) getActivity()).getSelectedYear();

        adapter = new SudahDibayarRecyclerView(terbayars, new BelumDibayarRecyclerViewCallback() {
            @Override
            public void onPayClicked(Pegawai pegawai, int position, boolean isPayable) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_terbayar, container, false);

        int month = ((BayarGajiActivity) getActivity()).getSelectedMonth();
        int year = ((BayarGajiActivity) getActivity()).getSelectedYear();

        terbayars = PembayaranGajiRepository.getInstance().getPembayaranGajiPaidByMonthYear(month,year);
        terbayarRV = (RecyclerView) v.findViewById(R.id.terbayarRv);
        emptyNotice = (TextView) v.findViewById(R.id.sudahBayarEmpty);

        createAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        terbayarRV.setLayoutManager(layoutManager);
        terbayarRV.setAdapter(adapter);

        return v;
    }
}