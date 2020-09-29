package com.gjjx.carvideo;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by ylbf_ on 2016/6/29.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<ListFragment> fragments;
    private List<String> titles;

    public FragmentAdapter(FragmentManager fm, List<ListFragment> fragments, List<String> titles) {
        // https://stackoverflow.com/questions/51131172/fragmentstatepageradapter-is-deprecated-from-api-27
        // super(fm);
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
