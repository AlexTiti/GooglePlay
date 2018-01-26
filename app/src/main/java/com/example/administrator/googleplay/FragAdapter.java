package com.example.administrator.googleplay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2017/12/11
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class FragAdapter extends FragmentPagerAdapter{
    private List<Fragment1> mListFragment ;

    public void setmListFragment(List<Fragment1> mListFragment) {
        this.mListFragment = mListFragment;
    }

    public FragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment == null ? 0 : mListFragment.size();
    }
}
