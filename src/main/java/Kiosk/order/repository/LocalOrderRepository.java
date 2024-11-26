package Kiosk.order.repository;

import Kiosk.cart.CartItem;
import Kiosk.menu.MenuItem;
import Kiosk.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocalOrderRepository implements OrderRepository {

    private static final List<Order> orderList = new ArrayList<>();


    @Override
    public void saveOrder(Order order) {
        orderList.add(order);
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Order findByOrder(Long id) {
        return orderList.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
    }
}
