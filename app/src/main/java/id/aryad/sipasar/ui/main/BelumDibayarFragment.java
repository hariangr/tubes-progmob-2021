package id.aryad.sipasar.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import id.aryad.sipasar.BayarGajiActivity;
import id.aryad.sipasar.R;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.PembayaranGajiRepository;
import id.aryad.sipasar.ui.adapter.BelumDibayarRecyclerView;
import id.aryad.sipasar.ui.adapter.PegawaiRecyclerViewCallback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BelumDibayarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BelumDibayarFragment extends Fragment {
    private RecyclerView belumDibayarRv;
    private BelumDibayarRecyclerView adapter;

    private List<Pegawai> pegawaiBelumGajian;

    public BelumDibayarFragment() {
    }

    public static BelumDibayarFragment newInstance(String param1, String param2) {
        BelumDibayarFragment fragment = new BelumDibayarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void monthYearUpdated() {
        try {
            int month = ((BayarGajiActivity) getActivity()).getSelectedMonth();
            int year = ((BayarGajiActivity) getActivity()).getSelectedYear();

            pegawaiBelumGajian = PembayaranGajiRepository.getInstance().unpaidByMonthYear(month, year);
            createAdapter();
            belumDibayarRv.setAdapter(adapter);
            belumDibayarRv.invalidate();
        } catch (Exception err) {

        }
    }

    void createAdapter() {
        adapter = new BelumDibayarRecyclerView(pegawaiBelumGajian, new PegawaiRecyclerViewCallback() {
            @Override
            public void onEditClicked(Pegawai pegawai, int position) {

            }

            @Override
            public void onDeleteClicked(Pegawai pegawai, int position) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_belum_dibayar, container, false);

        int month = ((BayarGajiActivity) getActivity()).getSelectedMonth();
        int year = ((BayarGajiActivity) getActivity()).getSelectedYear();

        belumDibayarRv = (RecyclerView) v.findViewById(R.id.belum_dibayar_rv);

        pegawaiBelumGajian = PembayaranGajiRepository.getInstance().unpaidByMonthYear(month, year);

        createAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        belumDibayarRv.setLayoutManager(layoutManager);
        belumDibayarRv.setAdapter(adapter);

        return v;
    }
}