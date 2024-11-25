package Kiosk.order;

import Kiosk.KioskConfig;
import Kiosk.cart.CartItem;
import Kiosk.cart.CartService;

import java.util.Scanner;

public class OrderServiceImpl implements OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CartService cartService, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("\033[38;5;214m[ ORDERS ]\033[0m");
        cartService.cartList();
        cartService.totalPrice();
        System.out.println("\u001B[36m1\u001B[0m. 결제하기    \u001B[36m2\u001B[0m. 취소");
        String select = input.next();
        switch (select) {
            case "1" :
                Order order = new Order(cartService.getCart());
                addOrder(order);
                cartService.cartClear();
                System.out.println("주문이 완료되었습니다. " + "[ 주문번호: " + "\u001B[36m" + order.getId() + "\u001B[0m ]");
                break;
            case "2" :
                break;
            default:
                System.out.println("잘못 입력했습니다.");
        }
    }

    @Override
    public void addOrder(Order orderItem) {
        orderRepository.saveOrder(orderItem);
    }
}
