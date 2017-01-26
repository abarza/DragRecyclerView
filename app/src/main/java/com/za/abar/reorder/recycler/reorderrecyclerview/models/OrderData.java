package com.za.abar.reorder.recycler.reorderrecyclerview.models;

/**
 * Created by abarza on 27-12-16.
 *
 * Dummy model class with shipment order data
 */

public class OrderData {

  private status orderStatus;
  private String orderID;
  private String contactName;
  private String contactAddress;
  private String tagLabel;
  private String tagColor;
  private OrderMode mode;
  private boolean isScheduled;
  private boolean isLocked;
  private boolean isTrunk;
  private boolean isSynced;

  public enum status {
    PENDING, DELIVERED, NOT_DELIVERED, PARTIALLY_DELIVERED
  }

  public enum OrderMode {
    DEFAULT, PICKUP, PICKUP_AND_DELIVERY
  }


  public OrderData(status orderStatus, String orderID, String contactName, String contactAddress,
                   String tagLabel, String tagColor, OrderMode mode, boolean isScheduled,
                   boolean isLocked, boolean isTrunk, boolean isSynced) {
    this.orderStatus = orderStatus;
    this.orderID = orderID;
    this.contactName = contactName;
    this.contactAddress = contactAddress;
    this.tagLabel = tagLabel;
    this.tagColor = tagColor;
    this.mode = mode;
    this.isScheduled = isScheduled;
    this.isLocked = isLocked;
    this.isTrunk = isTrunk;
    this.isSynced = isSynced;
  }

  public status getOrderStatus() {
    return orderStatus;
  }

  public String getOrderID() {
    return orderID;
  }

  public String getContactName() {
    return contactName;
  }

  public String getContactAddress() {
    return contactAddress;
  }

  public String getTagLabel() {
    return tagLabel;
  }

  public String getTagColor() {
    return tagColor;
  }

  public OrderMode getMode() {
    return mode;
  }

  public boolean isScheduled() {
    return isScheduled;
  }

  public boolean isTrunk() {
    return isTrunk;
  }

  public boolean isSynced() {
    return isSynced;
  }

}

