package Kiosk.order.service;

import Kiosk.cart.CartService;
import Kiosk.order.Order;
import Kiosk.order.repository.OrderRepository;

public class OrderServiceImpl implements OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CartService cartService, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(Order orderItem) {
        orderRepository.saveOrder(orderItem);
    }
}
