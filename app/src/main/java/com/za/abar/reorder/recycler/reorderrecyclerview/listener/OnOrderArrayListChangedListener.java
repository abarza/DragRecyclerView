package com.za.abar.reorder.recycler.reorderrecyclerview.listener;

import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;

import java.util.ArrayList;

/**
 * Created by abarza on 28-12-16.
 */

public interface OnOrderArrayListChangedListener {
  void onNoteListChanged(ArrayList<OrderData> orders);
}
