package com.za.abar.reorder.recycler.reorderrecyclerview.models;

/**
 * Created by abarza on 27-12-16.
 *
 * Dummy model class with shipment order data
 */

public class OrderData {


  /**
   * Order Fields:
   *
   * @param status status of the order (pending, delivered, not delivered, partially delivered)
   * @param orderID identifies the shipment order.
   * @param contactName name of the contact associated to the order
   * @param contactAddress address of the contact associated to the order
   * @param tagLabel custom field label set by the user in the webapp
   * @param tagColor custom color of the custom field set by the user in the webapp
   * @param orderMode mode of the order is 'normal', 'pick up only' or 'pick up and delivery'
   * @param isScheduled true if the order has scheduled delivery time
   * @param isLocked true if the order has been blocked in the webapp
   * @param isTrunk the order can be 'last mile' or 'trunk'
   */

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

  public void serOrderStatus(status orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getOrderID() {
    return orderID;
  }

  public void setOrderID(String orderID) {
    this.orderID = orderID;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getContactAddress() {
    return contactAddress;
  }

  public void setContactAddress(String contactAddress) {
    this.contactAddress = contactAddress;
  }

  public String getTagLabel() {
    return tagLabel;
  }

  public void setTagLabel(String tagLabel) {
    this.tagLabel = tagLabel;
  }

  public String getTagColor() {
    return tagColor;
  }

  public void setTagColor(String tagColor) {
    this.tagColor = tagColor;
  }

  public OrderMode getMode() {
    return mode;
  }

  public void setMode(OrderMode mode) {
    this.mode = mode;
  }

  public boolean isScheduled() {
    return isScheduled;
  }

  public void setScheduled(boolean scheduled) {
    isScheduled = scheduled;
  }

  public boolean isLocked() {
    return isLocked;
  }

  public void setLocked(boolean locked) {
    isLocked = locked;
  }

  public boolean isTrunk() {
    return isTrunk;
  }

  public void setTrunk(boolean trunk) {
    isTrunk = trunk;
  }

  public boolean isSynced() {
    return isSynced;
  }

  public void setSynced(boolean synced) {
    isSynced = synced;
  }
}

