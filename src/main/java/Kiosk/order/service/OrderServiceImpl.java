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

    // 주문 생성
    @Override
    public void createOrder(Order orderItem) {
        orderRepository.saveOrder(orderItem);
        cartService.getCart().clear();
        System.out.println("주문이 완료되었습니다. ");
    }

    // 주문 찾기
    @Override
    public Order findByOrder(Long Id) {
        return orderRepository.findByOrder(Id);
    }

}
