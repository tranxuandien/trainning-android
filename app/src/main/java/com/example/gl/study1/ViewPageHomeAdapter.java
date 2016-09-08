package com.example.gl.study1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageHomeAdapter extends FragmentStatePagerAdapter{
    private final List<Fragment> mFragment=new ArrayList<>();
    public ViewPageHomeAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment frag)
    {
        mFragment.add(frag);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
