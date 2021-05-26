package id.aryad.sipasar.ui.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.aryad.sipasar.BayarGajiActivity;
import id.aryad.sipasar.DetailHistoryGajiActivity;
import id.aryad.sipasar.R;
import id.aryad.sipasar.constants.IntentKey;
import id.aryad.sipasar.models.HistoryGajiPegawai;
import id.aryad.sipasar.models.Pegawai;
import id.aryad.sipasar.repositories.HistoryGajiRepository;
import id.aryad.sipasar.repositories.PembayaranGajiRepository;
import id.aryad.sipasar.ui.adapter.BelumDibayarRecyclerView;
import id.aryad.sipasar.ui.adapter.BelumDibayarRecyclerViewCallback;
import id.aryad.sipasar.ui.adapter.PegawaiRecyclerViewCallback;
import id.aryad.sipasar.ui.dialog.DoPayCallback;
import id.aryad.sipasar.ui.dialog.DoPayDialog;
import id.aryad.sipasar.ui.dialog.YearMonthDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BelumDibayarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BelumDibayarFragment extends Fragment {
    private RecyclerView belumDibayarRv;
    private BelumDibayarRecyclerView adapter;
    private TextView emptyNotice;

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

    private void updateEmptyIndicator() {
        if (pegawaiBelumGajian.size() == 0) {
            emptyNotice.setVisibility(View.VISIBLE);
        } else {
            emptyNotice.setVisibility(View.INVISIBLE);
        }
    }

    private void forceUpdate() {
        int month = ((BayarGajiActivity) getActivity()).getSelectedMonth();
        int year = ((BayarGajiActivity) getActivity()).getSelectedYear();

        pegawaiBelumGajian = PembayaranGajiRepository.getInstance().unpaidByMonthYear(month, year);
        createAdapter();
        belumDibayarRv.setAdapter(adapter);
        belumDibayarRv.invalidate();
        updateEmptyIndicator();
    }

    public void monthYearUpdated() {
        try {
            forceUpdate();
        } catch (Exception err) {
            // Ignore
        }
    }

    private void createAdapter() {
        int month = ((BayarGajiActivity) getActivity()).getSelectedMonth();
        int year = ((BayarGajiActivity) getActivity()).getSelectedYear();

        adapter = new BelumDibayarRecyclerView(pegawaiBelumGajian, new BelumDibayarRecyclerViewCallback() {
            @Override
            public void onPayClicked(Pegawai pegawai, int position, boolean isPayable) {
                if (isPayable) {
                    DoPayDialog pd = new DoPayDialog(pegawai, month, year, new DoPayCallback() {
                        @Override
                        public void okClicked() {
                            int nilaiGaji = HistoryGajiRepository.getInstance().getCurrentHistoryGajiByPegawaiId(pegawai.getId_pegawai()).getNilai_gaji();
                            PembayaranGajiRepository.getInstance().pay(pegawai.getId_pegawai(), new Date(year, month, 1), nilaiGaji);
                            adapter.notifyDataSetChanged();
                            forceUpdate();

                            ((BayarGajiActivity) getActivity()).updatePaid();

                            Toast.makeText(getContext(), "Pembayaran berhasil", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void cancelClicked() {

                        }
                    });
                    pd.show(getActivity().getSupportFragmentManager(), "Konfirmasi Pembayaran Gaji");
                } else {
                    Intent _intent = new Intent(getContext(), DetailHistoryGajiActivity.class);
                    _intent.putExtra(IntentKey.ID_PEGAWAI_DETAIL_ACTIVITY, pegawai.getId_pegawai());
                    startActivity(_intent);
                }
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
        emptyNotice = (TextView) v.findViewById(R.id.belumDibayarEmpty);

        pegawaiBelumGajian = PembayaranGajiRepository.getInstance().unpaidByMonthYear(month, year);

        createAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        belumDibayarRv.setLayoutManager(layoutManager);
        belumDibayarRv.setAdapter(adapter);
        updateEmptyIndicator();

        return v;
    }
}