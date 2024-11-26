package Kiosk;

import Kiosk.cart.CartService;
import Kiosk.cart.CartServiceImpl;
import Kiosk.menu.Repository.LocalMenuItemRepository;
import Kiosk.menu.Repository.MenuItemRepository;
import Kiosk.menu.Service.MainService;
import Kiosk.menu.Service.MainServiceImpl;
import Kiosk.order.repository.LocalOrderRepository;
import Kiosk.order.repository.OrderRepository;
import Kiosk.order.service.OrderService;
import Kiosk.order.service.OrderServiceImpl;

public class KioskConfig {

    private CartService cartService;
    private MenuItemRepository menuItemRepository;
    private OrderRepository orderRepository;
    private MainService mainService;
    private OrderService orderService;

    public CartService cartService() {
        if (cartService == null) {
            cartService = new CartServiceImpl();
        }
        return cartService;
    }

    public OrderRepository orderRepository() {
        if (orderRepository == null) {
            orderRepository = new LocalOrderRepository();
        }
        return orderRepository;
    }

    public MainService mainService() {
        return mainService = new MainServiceImpl(new LocalMenuItemRepository(), cartService());
    }

    public OrderService orderService() {
        return orderService = new OrderServiceImpl(cartService(), orderRepository());
    }

}
