package id.aryad.sipasar.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import id.aryad.sipasar.BayarGajiActivity;
import id.aryad.sipasar.R;
import id.aryad.sipasar.repositories.PembayaranGajiRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TerbayarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TerbayarFragment extends Fragment {
    TextView terbayarTemp;

    public TerbayarFragment() {
        // Required empty public constructor
    }

    public static TerbayarFragment newInstance() {
        TerbayarFragment fragment = new TerbayarFragment();
        return fragment;
    }

    public void monthYearUpdated() {
        try {
            int month = ((BayarGajiActivity) getActivity()).getSelectedMonth();
            int year = ((BayarGajiActivity) getActivity()).getSelectedYear();

            terbayarTemp.setText(month + " " + year);
        } catch (Exception err) {
            // pass
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_terbayar, container, false);

        terbayarTemp = (TextView) v.findViewById(R.id.terbayartemp);

        monthYearUpdated();

        return v;
    }
}