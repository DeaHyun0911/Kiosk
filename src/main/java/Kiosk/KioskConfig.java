package Kiosk;

import Kiosk.cart.CartService;
import Kiosk.cart.CartServiceImpl;
import Kiosk.menu.Repository.LocalMenuItemRepository;
import Kiosk.menu.Repository.MenuItemRepository;
import Kiosk.menu.Service.MenuService;
import Kiosk.menu.Service.MenuServiceImpl;
import Kiosk.order.repository.LocalOrderRepository;
import Kiosk.order.repository.OrderRepository;
import Kiosk.order.service.OrderService;
import Kiosk.order.service.OrderServiceImpl;
import Kiosk.utils.InputService;
import Kiosk.utils.PrintService;

public class KioskConfig {

    private CartService cartService;
    private MenuItemRepository menuItemRepository;
    private OrderRepository orderRepository;
    private MenuService menuService;
    private OrderService orderService;

    private InputService inputService;
    private PrintService printService;

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

    public InputService inputService() {
        if (inputService == null) {
            inputService = new InputService();
        }
        return inputService;
    }

    public PrintService printService() {
        if (printService == null) {
            printService = new PrintService();
        }
        return printService;
    }

    public MenuService menuService() {
        return menuService = new MenuServiceImpl(new LocalMenuItemRepository(), cartService(), printService());
    }

    public OrderService orderService() {
        return orderService = new OrderServiceImpl(cartService(), orderRepository());
    }

}
