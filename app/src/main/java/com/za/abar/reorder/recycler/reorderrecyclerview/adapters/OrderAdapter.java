package com.za.abar.reorder.recycler.reorderrecyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.holders.OrderHolder;
import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;

import java.util.ArrayList;

/**
 * Created by abarza on 27-12-16.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

  private ArrayList<OrderData> orderdata;

  public OrderAdapter(ArrayList<OrderData> orderdata) {
    this.orderdata = orderdata;
  }

  @Override
  public void onBindViewHolder(OrderHolder holder, final int position) {
    final OrderData routeData = orderdata.get(position);
    holder.updateUI(routeData);
  }

  @Override
  public int getItemCount() {
    return orderdata.size();
  }

  @Override
  public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View orderCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_cardview,
        parent, false);
    return new OrderHolder(orderCard);
  }


}
