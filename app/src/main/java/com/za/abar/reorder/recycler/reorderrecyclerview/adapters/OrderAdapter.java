package com.za.abar.reorder.recycler.reorderrecyclerview.adapters;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.holders.OrderHolder;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnStartDragListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;
import com.za.abar.reorder.recycler.reorderrecyclerview.utilities.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by abarza on 27-12-16.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> implements
    ItemTouchHelperAdapter {

  private ArrayList<OrderData> mOrderdata;
  private final OnStartDragListener mDragStartListener;
  private OrderHolder mHolder;

  public OrderAdapter(ArrayList<OrderData> orderdata,
                      OnStartDragListener dragListener) {

    mOrderdata = orderdata;
    mDragStartListener = dragListener;
  }

  @Override
  public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View orderCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_cardview,
        parent, false);
    return new OrderHolder(orderCard);
  }

  @Override
  public int getItemCount() {
    return mOrderdata.size();
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

    // Start a drag whenever the handle view it touched<
    holder.mReorder.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (MotionEventCompat.getActionMasked(event) ==
            MotionEvent.ACTION_DOWN) {
          mDragStartListener.onStartDrag(holder);
        }
        return false;
      }
    });

  }

}
