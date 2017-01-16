package com.za.abar.reorder.recycler.reorderrecyclerview.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.adapters.OrderAdapter;
import com.za.abar.reorder.recycler.reorderrecyclerview.holders.OrderHolder;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnOrderArrayListChangedListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnStartDragListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;
import com.za.abar.reorder.recycler.reorderrecyclerview.services.OrderService;
import com.za.abar.reorder.recycler.reorderrecyclerview.utilities.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;

/**
 * Created by abarza on 28-12-16.
 */
public class OrdersFragment extends Fragment implements
    OnStartDragListener {
  public static final String TAG = OrdersFragment.class.getSimpleName();
  private ItemTouchHelper mItemTouchHelper;
  private MenuItem mReorderItem;
  private static final int MENU_ITEM_REORDER = 1;
  OrderAdapter mOrderAdapter;
  private ItemTouchHelper.Callback mCallback;
  private boolean isSortEnabled = false;
  String bool;

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
        this);

    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_orders);
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(mOrderAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    mCallback = new SimpleItemTouchHelperCallback(mOrderAdapter);
    mItemTouchHelper = new ItemTouchHelper(mCallback);

    if (isSortEnabled) {
      mItemTouchHelper.attachToRecyclerView(recyclerView);

    } else {
      mItemTouchHelper.attachToRecyclerView(null);
    }


  }

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);

    mReorderItem = menu.add(Menu.NONE, MENU_ITEM_REORDER, Menu.NONE, "Reordenar");
    mReorderItem.setIcon(R.drawable.ic_reorder);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);

    switch (item.getItemId()) {
      case MENU_ITEM_REORDER:
        // String count = String.valueOf(mOrderAdapter.getItemCount());
        isSortEnabled = true;
        bool = Boolean.toString(isSortEnabled);
        View fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            isSortEnabled = false;
            Toast.makeText(getActivity(), "Puede reordenar?: " + bool, Toast.LENGTH_SHORT).show();
            view.setVisibility(View.GONE);
          }
        });
        Toast.makeText(getActivity(), "Puede reordenar?: " + bool, Toast.LENGTH_SHORT).show();

    }

    return true;
  }


}
