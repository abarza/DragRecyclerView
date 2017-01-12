package com.za.abar.reorder.recycler.reorderrecyclerview.adapters;

/**
 * Created by abarza on 12-01-17.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.fragments.OrdersFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

  Context context;

  public SectionsPagerAdapter(FragmentManager fm, Context mContext) {

    super(fm);
    context = mContext;
  }

  @Override
  public Fragment getItem(int position) {
    // getItem is called to instantiate the fragment for the given page.
    // Return a PlaceholderFragment (defined as a static inner class below).
    switch (position) {
      case 0:
        return OrdersFragment.newInstance();
      case 1:
        return OrdersFragment.newInstance();
      default:
        return new Fragment();
    }
  }

  @Override
  public int getCount() {
    // Show 3 total pages.
    return 2;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getString(R.string.onroute);
      case 1:
        return context.getString(R.string.completed);
    }
    return null;
  }
}