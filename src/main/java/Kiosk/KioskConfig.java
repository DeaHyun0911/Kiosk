package Kiosk;

import Kiosk.cart.CartService;
import Kiosk.cart.CartServiceImpl;
import Kiosk.menu.Repository.LocalMenuItemRepository;
import Kiosk.menu.Service.MainService;
import Kiosk.menu.Service.MainServiceImpl;
import Kiosk.menu.Service.MenuService;
import Kiosk.menu.Service.MenuServiceImpl;
import Kiosk.order.LocalOrderRepository;
import Kiosk.order.OrderService;
import Kiosk.order.OrderServiceImpl;

public class KioskConfig {

    private CartService cartService;
    private MenuService menuService;
    private MainService mainService;
    private OrderService orderService;

    public CartService cartService() {
        if (cartService == null) {
            cartService = new CartServiceImpl();
        }
        return cartService;
    }


    public MenuService menuService() {
        return menuService = new MenuServiceImpl(new LocalMenuItemRepository());
    }

    public MainService mainService() {
        return mainService = new MainServiceImpl(menuService(), cartService());
    }

    public OrderService orderService() {
        return orderService = new OrderServiceImpl(cartService(), new LocalOrderRepository());
    }

}
