package Kiosk;

import Kiosk.cart.Cart;
import Kiosk.cart.CartImpl;
import Kiosk.menu.Repository.LocalMenuItemRepository;
import Kiosk.menu.Service.PrintService;
import Kiosk.menu.Service.PrintServiceImpl;
import Kiosk.menu.Service.MenuService;
import Kiosk.menu.Service.MenuServiceImpl;

public class KioskConfig {

    public Cart cart() {
        return new CartImpl();
    }

    // printService에 MenuService을 주입
    public PrintService printService(Cart cart) {
        return new PrintServiceImpl(menuService(), cart);
    }

    //MenuService에 LocalMenuItemRepository를 주입
    public MenuService menuService() {
        return new MenuServiceImpl(new LocalMenuItemRepository());
    }

}
