package Kiosk.order.repository;

import Kiosk.menu.MenuItem;
import Kiosk.order.Order;

import java.util.List;

public interface OrderRepository {

    void saveOrder(Order order);

    List<Order> findAll();

    Order findByOrder(Long id);

    boolean OrderIsEmpty();

    List<Long> findAllOrderId();
}
