package Kiosk.order.service;

import Kiosk.order.Order;

public interface OrderService {

    void createOrder(Order orderItem);

    Order findByOrder(Long Id);

}
