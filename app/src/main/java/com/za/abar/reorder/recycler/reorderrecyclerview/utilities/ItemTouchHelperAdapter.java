package com.za.abar.reorder.recycler.reorderrecyclerview.utilities;

/**
 * Created by abarza on 28-12-16.
 */

public interface ItemTouchHelperAdapter {
  /**
   * Called when an item has been dragged far enough to trigger a move. This is called every time
   * an item is shifted, and not at the end of a "drop" event.
   *
   * @param fromPosition The start position of the moved item.
   * @param toPosition   Then end position of the moved item.
   */
  boolean onItemMove(int fromPosition, int toPosition);


  /**
   * Called when an item has been dismissed by a swipe.
   *
   * @param position The position of the item dismissed.
   */
  void onItemDismiss(int position);
}
