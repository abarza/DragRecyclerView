package com.za.abar.reorder.recycler.reorderrecyclerview.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by abarza on 28-12-16.
 */

public interface OnStartDragListener {
  /**
   * Called when a view is requesting a start of a drag.
   *
   * @param viewHolder The holder of the view to drag.
   */
  void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
