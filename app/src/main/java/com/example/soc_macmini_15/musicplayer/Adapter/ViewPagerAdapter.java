package com.example.soc_macmini_15.musicplayer.Adapter;

import android.content.ContentResolver;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.soc_macmini_15.musicplayer.Fragments.TabFragment;
import com.example.soc_macmini_15.musicplayer.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ContentResolver contentResolver;
    private String title[] = {"PLAYLIST", "FAVORITES"};

    public ViewPagerAdapter(FragmentManager fm, ContentResolver contentResolver) {
        super(fm);
        this.contentResolver=contentResolver;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.getInstance(position,contentResolver);
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
