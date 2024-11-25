package Kiosk.order;

import Kiosk.menu.MenuItem;

import java.util.List;

public interface OrderRepository {

    void saveOrder(Order order);

    List<MenuItem> findAll();
}
