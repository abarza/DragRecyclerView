package com.za.abar.reorder.recycler.reorderrecyclerview.multiselect_utilities;

import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abarza on 19-01-17.
 */

class WeakHolderTracker {

  private SparseArray<WeakReference<SelectableHolder>> mHoldersByPosition =
      new SparseArray<>();

  /**
   * Returns the holder with a given position. If non-null, the returned
   * holder is guaranteed to have getPosition() == position.
   *
   * @param position
   * @return
   */
  public SelectableHolder getHolder(int position) {
    WeakReference<SelectableHolder> holderRef = mHoldersByPosition.get(position);
    if (holderRef == null) {
      return null;
    }

    SelectableHolder holder = holderRef.get();
    if (holder == null || holder.getAdapterPosition() != position) {
      mHoldersByPosition.remove(position);
      return null;
    }

    return holder;
  }

  public void bindHolder(SelectableHolder holder, int position) {
    mHoldersByPosition.put(position, new WeakReference<>(holder));
  }

  public List<SelectableHolder> getTrackedHolders() {
    List<SelectableHolder> holders = new ArrayList<SelectableHolder>();

    for (int i = 0; i < mHoldersByPosition.size(); i++) {
      int key = mHoldersByPosition.keyAt(i);
      SelectableHolder holder = getHolder(key);

      if (holder != null) {
        holders.add(holder);
      }
    }

    return holders;
  }
}
