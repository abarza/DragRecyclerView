package com.za.abar.reorder.recycler.reorderrecyclerview.holders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * Created by abarza on 27-12-16.
 */

public class OrderHolder extends RecyclerView.ViewHolder {

  private TextView orderId;
  private TextView contactName;
  private TextView contactAddress;
  private TextView customLabel;
  private ImageView orderMode;
  private ImageView orderLocked;
  private ImageView orderScheduled;
  private ImageView orderTrunk;
  private ImageView orderSynced;
  private Context context;

  public OrderHolder(View itemView) {
    super(itemView);

    this.orderId = (TextView) itemView.findViewById(R.id.order_id);
    this.contactName = (TextView) itemView.findViewById(R.id.contact_name);
    this.contactAddress = (TextView) itemView.findViewById(R.id.contact_address);
    this.customLabel = (TextView) itemView.findViewById(R.id.custom_label);
    this.orderMode = (ImageView) itemView.findViewById(R.id.order_mode);
    this.orderLocked = (ImageView) itemView.findViewById(R.id.locked);
    this.orderScheduled = (ImageView) itemView.findViewById(R.id.scheduled);
    this.orderTrunk = (ImageView) itemView.findViewById(R.id.trunk);
    this.orderSynced = (ImageView) itemView.findViewById(R.id.synced);
    this.context = itemView.getContext();

  }

  public void updateUI(OrderData orderData) {
    OrderData.status status = orderData.getOrderStatus();
    String id = orderData.getOrderID();
    String name = orderData.getContactName();
    String address = orderData.getContactAddress();
    String label = orderData.getTagLabel();
    String tagcolor = orderData.getTagColor();
    OrderData.OrderMode mode = orderData.getMode();
    boolean locked = orderData.isSynced();
    boolean scheduled = orderData.isScheduled();
    boolean trunk = orderData.isTrunk();
    boolean synced = orderData.isSynced();

    Drawable defaultIcon = ContextCompat.getDrawable(context, R.drawable.ic_default);
    Drawable pickupIcon = ContextCompat.getDrawable(context, R.drawable.ic_pickup);
    Drawable pickupanddeliveryIcon = ContextCompat.getDrawable(context, R.drawable.ic_pickupanddelivery);

    Drawable lockedIcon = ContextCompat.getDrawable(context, R.drawable.ic_locked);
    Drawable scheduledIcon = ContextCompat.getDrawable(context, R.drawable.ic_scheduled);
    Drawable trunkIcon = ContextCompat.getDrawable(context, R.drawable.ic_trunk);
    Drawable syncedIcon = ContextCompat.getDrawable(context, R.drawable.ic_synced);


    int delivered = getColor(context, R.color.delivered);
    int notDelivered = getColor(context, R.color.not_delivered);
    int partiallyDelivered = getColor(context, R.color.partially_delivered);
    int pending = getColor(context, R.color.pending);

    orderId.setText(id);

    switch (status) {
      case DELIVERED:
        orderId.setTextColor(delivered);
        break;
      case NOT_DELIVERED:
        orderId.setTextColor(notDelivered);
        break;
      case PARTIALLY_DELIVERED:
        orderId.setTextColor(partiallyDelivered);
        break;
      case PENDING:
        orderId.setTextColor(pending);
    }

    contactName.setText(name);
    contactAddress.setText(address);
    if (label == null) {
      customLabel.setVisibility(View.GONE);
    } else {
      customLabel.setText(label);
      customLabel.setBackgroundColor(Color.parseColor(tagcolor));
    }

    switch (mode) {
      case DEFAULT:
        orderMode.setImageDrawable(defaultIcon);
        break;
      case PICKUP:
        orderMode.setImageDrawable(pickupIcon);
        break;
      case PICKUP_AND_DELIVERY:
        orderMode.setImageDrawable(pickupanddeliveryIcon);
        break;
    }

    orderLocked.setImageDrawable(lockedIcon);
    orderScheduled.setImageDrawable(scheduledIcon);
    orderTrunk.setImageDrawable(trunkIcon);
    orderSynced.setImageDrawable(syncedIcon);

    setColorIcon(orderLocked, locked);
    setColorIcon(orderTrunk, trunk);
    setColorIcon(orderScheduled, scheduled);
    setColorIcon(orderSynced, synced);


  }

  private void setColorIcon(ImageView v, boolean b) {
    if (b) {
      v.setColorFilter(getColor(context, R.color.colorEnabled));
    } else {
      v.setColorFilter(getColor(context, R.color.colorDisabled));
    }
  }

}
