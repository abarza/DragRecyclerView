package com.za.abar.reorder.recycler.reorderrecyclerview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.RouteActivity;
import com.za.abar.reorder.recycler.reorderrecyclerview.adapters.OrderAdapter;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnStartDragListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.multiselect_utilities.ModalMultiSelectorCallback;
import com.za.abar.reorder.recycler.reorderrecyclerview.multiselect_utilities.MultiSelector;
import com.za.abar.reorder.recycler.reorderrecyclerview.services.OrderService;
import com.za.abar.reorder.recycler.reorderrecyclerview.reorder_utilities.SimpleItemTouchHelperCallback;

/**
 * Created by abarza on 28-12-16.
 */
public class OrdersFragment extends BaseFragment implements
    OnStartDragListener {
  public static final String TAG = OrdersFragment.class.getSimpleName();
  private ItemTouchHelper mItemTouchHelper;
  private RecyclerView mRecyclerView;
  private OrderAdapter mOrderAdapter;
  private MultiSelector mMultiSelector = new MultiSelector();

  private ModalMultiSelectorCallback mDeleteMode = new ModalMultiSelectorCallback(mMultiSelector) {

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
      super.onCreateActionMode(actionMode, menu);
      getActivity().getMenuInflater().inflate(R.menu.batch_management_menu, menu);
      return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
      if (menuItem.getItemId()==  R.id.action_select_all){
        // Need to finish the action mode before doing the following,
        // not after. No idea why, but it crashes.
        actionMode.finish();


        mMultiSelector.clearSelections();
        return true;

      }
      return false;
    }

  };

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

  /**
   * Note: since the fragment is retained. the bundle passed in after state is restored is null.
   * THe only way to pass parcelable objects is through the activities onsavedInstanceState and appropiate startup lifecycle
   * However after having second thoughts, since the fragment is retained then all the states and instance variables are
   * retained as well. no need to make the selection states percelable therefore just check for the selectionstate
   * from the multiselector
   */
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {

    if (mMultiSelector != null) {

      Bundle bundle = savedInstanceState;
      if (bundle != null) {
        mMultiSelector.restoreSelectionStates(bundle.getBundle(TAG));
      }

      if (mMultiSelector.isSelectable()) {
        if (mDeleteMode != null) {
          mDeleteMode.setClearOnPrepare(false);
          ((AppCompatActivity) getActivity()).startSupportActionMode(mDeleteMode);
        }

      }
    }

    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putBundle(TAG, mMultiSelector.saveSelectionStates());
    super.onSaveInstanceState(outState);
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
