package com.example.brewbeers.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.brewbeers.DetailFragment;
import com.example.brewbeers.models.BeersModel;

import java.util.List;

public class CharPagerAdapter extends FragmentPagerAdapter {
    private List<BeersModel> mDocList;

    public CharPagerAdapter(FragmentManager fm, int behavior, List<BeersModel> restaurants) {
        super(fm, behavior);
        mDocList = restaurants;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(mDocList.get(position));
    }

    @Override
    public int getCount() {
        return mDocList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDocList.get(position).getName();
    }
}
