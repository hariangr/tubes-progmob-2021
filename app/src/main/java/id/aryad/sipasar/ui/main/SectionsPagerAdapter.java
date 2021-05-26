package id.aryad.sipasar.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.aryad.sipasar.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final String[] TAB_TITLES = new String[]{"Belum Dibayar", "Terbayar"};
    private final Context mContext;

    private BelumDibayarFragment belumDibayarFragment = BelumDibayarFragment.newInstance("", "");
    private TerbayarFragment terbayarFragment = TerbayarFragment.newInstance();

    public void monthDateUpdated() {
        belumDibayarFragment.monthYearUpdated();
        terbayarFragment.monthYearUpdated();
    }

    public void updatePaid() {
        terbayarFragment.forceUpdate();
    }

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return belumDibayarFragment;
            case 1:
                return terbayarFragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_TITLES.length;
    }
}