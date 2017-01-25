package com.za.abar.reorder.recycler.reorderrecyclerview.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.RouteActivity;
import com.za.abar.reorder.recycler.reorderrecyclerview.holders.OrderHolder;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnStartDragListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;
import com.za.abar.reorder.recycler.reorderrecyclerview.reorder_utilities.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by abarza on 27-12-16.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> implements
    ItemTouchHelperAdapter {

  private ArrayList<OrderData> mOrderdata;
  private final OnStartDragListener mDragStartListener;
  private Activity mActivity;
  private FrameLayout.LayoutParams mLayoutParams;
  private SparseBooleanArray selectedItems;


  public OrderAdapter(ArrayList<OrderData> orderdata,
                      OnStartDragListener dragListener, Activity activity) {

    mDragStartListener = dragListener;
    mActivity = activity;

    if (orderdata == null) {
      throw new IllegalArgumentException("Modeldata must no be null");
    } else {
      mOrderdata = orderdata;
      selectedItems = new SparseBooleanArray();
    }

  }

  @Override
  public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View orderCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_cardview,
        parent, false);
    return new OrderHolder(orderCard);


  }

  /**
   * Adds and item into the underlying data set
   * at the position passed into the method.
   *
   * @param orderData The item to add to the data set.
   * @param position  The index of the item to remove.
   */
  public void addData(OrderData orderData, int position) {
    mOrderdata.add(position, orderData);
    notifyItemInserted(position);
  }

  /**
   * Removes the item that currently is at the passed in position from the
   * underlying data set.
   *
   * @param position The index of the item to remove.
   */
  public void removeData(int position) {
    mOrderdata.remove(position);
    notifyItemRemoved(position);
  }

  public OrderData getItem(int position) {
    return mOrderdata.get(position);
  }

  @Override
  public int getItemCount() {
    return mOrderdata.size();
  }

  public void toggleSelection(int pos) {
    if (selectedItems.get(pos, false)) {
      selectedItems.delete(pos);
    }
    else {
      selectedItems.put(pos, true);
    }
    notifyItemChanged(pos);
  }

  public void clearSelections() {
    selectedItems.clear();
    notifyDataSetChanged();
  }

  public int getSelectedItemCount() {
    return selectedItems.size();
  }

  public List<Integer> getSelectedItems() {
    List<Integer> items = new ArrayList<>(selectedItems.size());
    for (int i = 0; i < selectedItems.size(); i++) {
      items.add(selectedItems.keyAt(i));
    }
    return items;
  }


  @Override
  public boolean onItemMove(int fromPosition, int toPosition) {
    Collections.swap(mOrderdata, fromPosition, toPosition);
    notifyItemMoved(fromPosition, toPosition);
    return true;
  }

  @Override
  public void onItemDismiss(int position) {
    mOrderdata.remove(position);
    notifyItemRemoved(position);
  }

  @Override
  public void onBindViewHolder(final OrderHolder holder, final int position) {

    final OrderData routeData = mOrderdata.get(position);

    holder.updateUI(routeData);
    mLayoutParams = new FrameLayout.LayoutParams(FrameLayout
        .LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);


    if (mActivity instanceof RouteActivity) {
      if (((RouteActivity) mActivity).isSortEnabled) {
        holder.mReorder.setVisibility(View.VISIBLE);
        mLayoutParams.setMargins(15, 0, 60, 0);
        holder.mCardView.setLayoutParams(mLayoutParams);
        // Start a drag whenever the handle view it touched
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            if (MotionEventCompat.getActionMasked(event) ==
                MotionEvent.ACTION_DOWN) {
              mDragStartListener.onStartDrag(holder);
            }
            return false;
          }
        });
      } else {
        holder.mReorder.setVisibility(View.GONE);
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
          }
        });
        mLayoutParams.setMargins(15, 0, 15, 0);
        holder.mCardView.setLayoutParams(mLayoutParams);

      }

      holder.itemView.setActivated(selectedItems.get(position, false));

    }


  }


}
