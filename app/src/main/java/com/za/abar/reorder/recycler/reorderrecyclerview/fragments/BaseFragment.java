package com.za.abar.reorder.recycler.reorderrecyclerview.fragments;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;

/**
 * Created by abarza on 20-01-17.
 */

public class BaseFragment extends Fragment {

  final AppCompatActivity act = (AppCompatActivity) getActivity();
  Toolbar toolBar = (Toolbar) act.findViewById(R.id.toolbar);

  protected Toolbar getToolBar() {
    return toolBar;
  }
}
