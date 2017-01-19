package com.za.abar.reorder.recycler.reorderrecyclerview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DummyFragment extends Fragment {
  public static final String TAG = OrdersFragment.class.getSimpleName();

  public DummyFragment() {
    // Required empty public constructor
  }

  public static DummyFragment newInstance() {
    Log.d(TAG, "newInstance: ");
    DummyFragment fragment = new DummyFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_dummy, container, false);
  }

}
