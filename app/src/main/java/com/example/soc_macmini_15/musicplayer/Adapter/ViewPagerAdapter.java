package com.example.soc_macmini_15.musicplayer.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.soc_macmini_15.musicplayer.Fragments.TabFragment;
import com.example.soc_macmini_15.musicplayer.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String title[] = {"PLAYLIST", "FAVORITES"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
