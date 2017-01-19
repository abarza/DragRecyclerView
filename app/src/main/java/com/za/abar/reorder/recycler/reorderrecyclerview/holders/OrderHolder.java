package com.za.abar.reorder.recycler.reorderrecyclerview.holders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;
import com.za.abar.reorder.recycler.reorderrecyclerview.utilities.ItemTouchHelperViewHolder;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * Created by abarza on 27-12-16.
 */

public class OrderHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

  private TextView mOrderId;
  private TextView mContactName;
  private TextView mContactAddress;
  private TextView mCustomLabel;
  private ImageView mOrderMode;
  private ImageView mOrderLocked;
  private ImageView mOrderScheduled;
  private ImageView mOrderTrunk;
  private ImageView mOrderSynced;
  private Context mContext;
  public CardView mCardView;
  public ImageView mReorder;

  public OrderHolder(View itemView) {
    super(itemView);

    mOrderId = (TextView) itemView.findViewById(R.id.order_id);
    mContactName = (TextView) itemView.findViewById(R.id.contact_name);
    mContactAddress = (TextView) itemView.findViewById(R.id.contact_address);
    mCustomLabel = (TextView) itemView.findViewById(R.id.custom_label);
    mOrderMode = (ImageView) itemView.findViewById(R.id.order_mode);
    mOrderLocked = (ImageView) itemView.findViewById(R.id.locked);
    mOrderScheduled = (ImageView) itemView.findViewById(R.id.scheduled);
    mOrderTrunk = (ImageView) itemView.findViewById(R.id.trunk);
    mOrderSynced = (ImageView) itemView.findViewById(R.id.synced);
    mContext = itemView.getContext();
    mCardView = (CardView) itemView.findViewById(R.id.card_item);
    mReorder = (ImageView) itemView.findViewById(R.id.reorder_icon);


  }

  public void updateUI(OrderData orderData) {
    OrderData.status status = orderData.getOrderStatus();
    String id = orderData.getOrderID();
    String name = orderData.getContactName();
    String address = orderData.getContactAddress();
    String label = orderData.getTagLabel();
    String tagColor = orderData.getTagColor();
    OrderData.OrderMode mode = orderData.getMode();
    boolean locked = orderData.isSynced();
    boolean scheduled = orderData.isScheduled();
    boolean trunk = orderData.isTrunk();
    boolean synced = orderData.isSynced();

    Drawable defaultIcon = ContextCompat.getDrawable(mContext, R.drawable.ic_default);
    Drawable pickupIcon = ContextCompat.getDrawable(mContext, R.drawable.ic_pickup);
    Drawable pickupanddeliveryIcon = ContextCompat.getDrawable(mContext, R.drawable.ic_pickupanddelivery);

    Drawable lockedIcon = ContextCompat.getDrawable(mContext, R.drawable.ic_locked);
    Drawable scheduledIcon = ContextCompat.getDrawable(mContext, R.drawable.ic_scheduled);
    Drawable trunkIcon = ContextCompat.getDrawable(mContext, R.drawable.ic_trunk);
    Drawable syncedIcon = ContextCompat.getDrawable(mContext, R.drawable.ic_synced);

    int delivered = getColor(mContext, R.color.delivered);
    int notDelivered = getColor(mContext, R.color.not_delivered);
    int partiallyDelivered = getColor(mContext, R.color.partially_delivered);
    int pending = getColor(mContext, R.color.pending);

    mOrderId.setText(id);

    switch (status) {
      case DELIVERED:
        mOrderId.setTextColor(delivered);
        break;
      case NOT_DELIVERED:
        mOrderId.setTextColor(notDelivered);
        break;
      case PARTIALLY_DELIVERED:
        mOrderId.setTextColor(partiallyDelivered);
        break;
      case PENDING:
        mOrderId.setTextColor(pending);
    }

    mContactName.setText(name);
    mContactAddress.setText(address);

    if (label == null) {
      mCustomLabel.setVisibility(View.GONE);
    } else if (tagColor == null) {
      mCustomLabel.setText(label);
      mCustomLabel.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorDisabled));
      mCustomLabel.setTextColor(ContextCompat.getColor(mContext, R.color.black));
    } else {
      mCustomLabel.setText(label);
      mCustomLabel.setBackgroundColor(Color.parseColor(tagColor));
    }

    switch (mode) {
      case DEFAULT:
        mOrderMode.setImageDrawable(defaultIcon);
        break;
      case PICKUP:
        mOrderMode.setImageDrawable(pickupIcon);
        break;
      case PICKUP_AND_DELIVERY:
        mOrderMode.setImageDrawable(pickupanddeliveryIcon);
        break;
    }

    mOrderLocked.setImageDrawable(lockedIcon);
    mOrderScheduled.setImageDrawable(scheduledIcon);
    mOrderTrunk.setImageDrawable(trunkIcon);
    mOrderSynced.setImageDrawable(syncedIcon);

    setColorIcon(mOrderLocked, locked);
    setColorIcon(mOrderTrunk, trunk);
    setColorIcon(mOrderScheduled, scheduled);
    setColorIcon(mOrderSynced, synced);

    mReorder.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable
        .ic_reorder));


  }

  private void setColorIcon(ImageView v, boolean b) {
    if (b) {
      v.setColorFilter(getColor(mContext, R.color.colorEnabled));
    } else {
      v.setColorFilter(getColor(mContext, R.color.colorDisabled));
    }
  }

  @Override
  public void onItemSelected() {
    mCardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.light_gray));

  }

  @Override
  public void onItemClear() {
    mCardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
  }


}
