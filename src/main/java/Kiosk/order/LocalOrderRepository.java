package Kiosk.order;

import Kiosk.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class LocalOrderRepository implements OrderRepository {

    private static List<Order> orderList = new ArrayList<>();


    @Override
    public void saveOrder(Order order) {
        orderList.add(order);
    }

    @Override
    public List<MenuItem> findAll() {
        return List.of();
    }
}
