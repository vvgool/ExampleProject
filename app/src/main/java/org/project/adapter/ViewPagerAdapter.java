package org.project.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.project.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiesen on 2016/8/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragmentList;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
    }

    public void addFragment(BaseFragment fragment){
        mFragmentList.add(fragment);
        notifyDataSetChanged();
    }

    public void addAllFragment(List<BaseFragment> fragments){
        mFragmentList.addAll(fragments);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentList.get(position).getTitle();
    }
}
