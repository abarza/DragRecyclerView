package com.za.abar.reorder.recycler.reorderrecyclerview.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.RouteActivity;
import com.za.abar.reorder.recycler.reorderrecyclerview.adapters.OrderAdapter;
import com.za.abar.reorder.recycler.reorderrecyclerview.listener.OnStartDragListener;
import com.za.abar.reorder.recycler.reorderrecyclerview.reorder_utilities.SimpleItemTouchHelperCallback;
import com.za.abar.reorder.recycler.reorderrecyclerview.services.OrderService;

/**
 * Created by abarza on 28-12-16.
 */
public class OrdersFragment extends Fragment implements
    OnStartDragListener, RecyclerView.OnItemTouchListener,
    View.OnClickListener, View.OnLongClickListener {
  public static final String TAG = OrdersFragment.class.getSimpleName();
  private ItemTouchHelper mItemTouchHelper;
  private RecyclerView mRecyclerView;
  private OrderAdapter mOrderAdapter;
  private GestureDetectorCompat mGestureDetectorCompat;
  private ActionMode mActionMode;
  private FloatingActionButton mFab;
  private ActionMode.Callback mCallback = new ActionMode.Callback() {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
      // Inflate a menu resource providing context menu items
      MenuInflater inflater = mode.getMenuInflater();
      inflater.inflate(R.menu.batch_management_menu, menu);
      processItems();
      return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
      return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
      switch (item.getItemId()) {
        case R.id.action_select_all:
          Toast.makeText(getActivity(), R.string.action_select_all, Toast.LENGTH_SHORT).show();
          mOrderAdapter.selectAllItems();
          String title = String.format(getResources().getString(R.string.selection_text), mOrderAdapter
              .getSelectedItemCount());
          mActionMode.setTitle(title);
          return true;
        case R.id.action_clear_all:
          Toast.makeText(getActivity(), R.string.action_clear_selection, Toast.LENGTH_SHORT).show();
          mOrderAdapter.clearSelections();
          mActionMode.finish();
          return true;
        case R.id.select_last_mile:
          Toast.makeText(getActivity(), R.string.select_last_mile, Toast.LENGTH_SHORT).show();
          return true;
        case R.id.select_trunk:
          Toast.makeText(getActivity(), R.string.select_trunk, Toast.LENGTH_SHORT).show();
          return true;
        default:
          return false;
      }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
      mActionMode = null;
      mOrderAdapter.clearSelections();
    }
  };

  public void processItems() {
    mFab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
    mFab.setImageResource(R.drawable.ic_send);
    if (mOrderAdapter.getSelectedItemCount() > 0) {
      mFab.setVisibility(View.VISIBLE);
      mFab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
          builder.setTitle(R.string.selected_shipments);
          builder.setMessage(mOrderAdapter.getSelectedItems().toString());
          builder.setCancelable(true);
          builder.create();
          builder.show();
          mOrderAdapter.clearSelections();
          mActionMode.finish();
          mFab.setVisibility(View.GONE);
        }
      });
    } else {
      mFab.setVisibility(View.GONE);
    }


  }

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
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    mRecyclerView.getAdapter().notifyDataSetChanged();
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
    // allows for optimizations if all items are of the same size:
    mRecyclerView.setHasFixedSize(true);

    mRecyclerView.setAdapter(mOrderAdapter);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    // actually VERTICAL is the default,
    // just remember: LinearLayoutManager
    // supports HORIZONTAL layout out of the box
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    // you can set the first visible item like this:
    layoutManager.scrollToPosition(0);
    mRecyclerView.setLayoutManager(layoutManager);

    if (getActivity() instanceof RouteActivity) {
      ((RouteActivity) getActivity()).setAdapter(mOrderAdapter);
    }

    mOrderAdapter.getItemCount();

    itemTouchHelper();

    mRecyclerView.addOnItemTouchListener(this);
    mGestureDetectorCompat = new GestureDetectorCompat(getActivity(), new
        RecyclerViewDemoOnGestureListener
        ());

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

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);
    if (item.getItemId() == R.id.action_select_all) {
      removeItemFromList();
    }
    return true;
  }

  private void removeItemFromList() {
    int position = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).
        findFirstCompletelyVisibleItemPosition();
    Toast.makeText(getActivity(), "Item removed", Toast.LENGTH_SHORT).show();
    mOrderAdapter.removeData(position);
  }

  private void myToggleSelection(int idx) {
    mOrderAdapter.toggleSelection(idx);

    String title = String.format(getResources().getString(R.string.selection_text), mOrderAdapter
        .getSelectedItemCount());
    mActionMode.setTitle(title);
  }

  @Override
  public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionevent) {
    mGestureDetectorCompat.onTouchEvent(motionevent);
    return false;
  }

  @Override
  public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionevent) {
  }

  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

  }

  @Override
  public void onClick(View v) {
    if (mActionMode != null) {
      int idx = mRecyclerView.indexOfChild(v);
      myToggleSelection(idx);
    }

  }

  @Override
  public boolean onLongClick(View v) {
    mActionMode = ((RouteActivity) getActivity()).startSupportActionMode(mCallback);
    return true;
  }

  private class RecyclerViewDemoOnGestureListener extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
      View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
      onClick(view);
      return super.onSingleTapConfirmed(e);
    }

    public void onLongPress(MotionEvent e) {
      View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
      if (mActionMode != null) {
        return;
      }
      // Start the CAB using the ActionMode.Callback defined above
      mActionMode = ((RouteActivity) getActivity()).startSupportActionMode(mCallback);
      int idx = mRecyclerView.indexOfChild(view);
      myToggleSelection(idx);
      super.onLongPress(e);
    }
  }
}
