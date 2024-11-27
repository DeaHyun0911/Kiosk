package Kiosk.order.service;

import Kiosk.order.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order orderItem);

    Order findByOrder(Long Id);

    boolean orderIsEmpty();

    List<Long> OrderList();

}
