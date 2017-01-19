package com.za.abar.reorder.recycler.reorderrecyclerview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.RouteActivity;
import com.za.abar.reorder.recycler.reorderrecyclerview.adapters.OrderAdapter;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnStartDragListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.services.OrderService;
import com.za.abar.reorder.recycler.reorderrecyclerview.utilities.SimpleItemTouchHelperCallback;

/**
 * Created by abarza on 28-12-16.
 */
public class OrdersFragment extends Fragment implements
    OnStartDragListener {
  public static final String TAG = OrdersFragment.class.getSimpleName();
  private ItemTouchHelper mItemTouchHelper;
  private RecyclerView mRecyclerView;
  private OrderAdapter mOrderAdapter;

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
    setHasOptionsMenu(true);
    
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_orders, container, false);

  }

  @Override
  public void onViewCreated(View view, Bundle bundle) {
    super.onViewCreated(view, bundle);

    mOrderAdapter = new OrderAdapter(OrderService.getInstance().getOrders(),
        this, getActivity());
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_orders);

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setAdapter(mOrderAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    if(getActivity() instanceof RouteActivity) {
      ((RouteActivity) getActivity()).setAdapter(mOrderAdapter);
    }


    mOrderAdapter.getItemCount();

    itemTouchHelper();

  }

  public void itemTouchHelper() {

    ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mOrderAdapter);
    mItemTouchHelper = new ItemTouchHelper(callback);
    mItemTouchHelper.attachToRecyclerView(mRecyclerView);

  }

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }

}
