package com.volkangurbuz.stajtakip.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
  private final List<Fragment> mFragmentList = new ArrayList<>();

  public FragmentAdapter(FragmentManager manager) {
    super(manager);
  }

  @Override
  public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override
  public int getCount() {
    return mFragmentList.size();
  }

  public void addFragment(Fragment fragment) {
    mFragmentList.add(fragment);
  }
}
