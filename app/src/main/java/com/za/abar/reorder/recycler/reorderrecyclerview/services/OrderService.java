package com.za.abar.reorder.recycler.reorderrecyclerview.services;

import com.za.abar.reorder.recycler.reorderrecyclerview.R;
import com.za.abar.reorder.recycler.reorderrecyclerview.models.OrderData;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by abarza on 27-12-16.
 */

public class OrderService {

  private static OrderService ourInstance = new OrderService();

  public static OrderService getInstance() {
    return ourInstance;
  }

  public ArrayList<OrderData> getOrders() {

    ArrayList<OrderData> list = new ArrayList<>();

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


    list.add(new OrderData(OrderData.status.PENDING, "13123123", "Francisco Abarza", "La " +
        "Concepción 80 Depto 310", "Alta", "#fc0000", OrderData.OrderMode
        .DEFAULT, false, false, false, true));
    list.add(new OrderData(OrderData.status.NOT_DELIVERED, "4324324324", "Jorge Codina", "Presidente " +
        "Errázuriz 4125, Las Condes",
        null, "#342342", OrderData.OrderMode
        .DEFAULT, false, false, true, true));
    list.add(new OrderData(OrderData.status.PENDING, "525621231", "Francisco Caiceo", "Alonso de " +
        "Córdova 5670, OF 1504, " +
        "Las Condes",
        null, "#342342", OrderData.OrderMode
        .PICKUP, true, true, true, false));
    list.add(new OrderData(OrderData.status.PARTIALLY_DELIVERED, "245363223", "Andres Colonia", "Alonso de " +
        "Córdova 5670, OF 1504, " +
        "Las Condes",
        "Pudahuel", null, OrderData.OrderMode
        .PICKUP_AND_DELIVERY, false, false, false, false));
    list.add(new OrderData(OrderData.status.PENDING, "45362311", "Andres Colonia", "Alonso de " +
        "Córdova 5670, OF 1504, " +
        "Las Condes",
        null, "#342342", OrderData.OrderMode
        .DEFAULT, false, false, false, false));
    list.add(new OrderData(OrderData.status.PENDING, "2323423423", "Juan Pablo Lazcano",
        "Alonso de " +
            "Córdova 5670, OF 1504, " +
            "Las Condes",
        null, "#342342", OrderData.OrderMode
        .DEFAULT, false, false, true, false));


    return list;
  }



}
