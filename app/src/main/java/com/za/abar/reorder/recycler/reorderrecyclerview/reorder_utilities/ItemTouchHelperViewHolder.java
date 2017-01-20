package com.za.abar.reorder.recycler.reorderrecyclerview.reorder_utilities;

/**
 * Created by abarza on 28-12-16.
 */

public interface ItemTouchHelperViewHolder {
  /**
   * Implementations should update the item view to indicate it's active state.
   */
  void onItemSelected();


  /**
   * state should be cleared.
   */
  void onItemClear();
}