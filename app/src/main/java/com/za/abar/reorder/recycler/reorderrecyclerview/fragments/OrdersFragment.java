package com.za.abar.reorder.recycler.reorderrecyclerview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.adapters.OrderAdapter;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnOrderArrayListChangedListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnStartDragListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;
import com.za.abar.reorder.recycler.reorderrecyclerview.services.OrderService;
import com.za.abar.reorder.recycler.reorderrecyclerview.utilities.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

/**
 * Created by abarza on 28-12-16.
 */
public class OrdersFragment extends Fragment implements
    OnStartDragListener {
  public static final String TAG = OrdersFragment.class.getSimpleName();
  private ItemTouchHelper mItemTouchHelper;

  public OrdersFragment() {
    // Required empty public constructor
  }

  public static OrdersFragment newInstance() {
    Log.d(TAG, "newInstance: ");
    OrdersFragment fragment = new OrdersFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_orders, container, false);

  }

  @Override
  public void onViewCreated(View view, Bundle icicle) {
    super.onViewCreated(view, icicle);

    OrderAdapter orderAdapter = new OrderAdapter(OrderService.getInstance().getOrders(),
        this);

    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_orders);
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(orderAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(orderAdapter);
    mItemTouchHelper = new ItemTouchHelper(callback);
    mItemTouchHelper.attachToRecyclerView(recyclerView);


  }

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }


}
