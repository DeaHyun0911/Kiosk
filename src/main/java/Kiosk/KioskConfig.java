package Kiosk;

import Kiosk.menu.LocalMenuItemRepository;
import Kiosk.menu.MenuService;
import Kiosk.menu.MenuServiceImpl;

public class KioskConfig {

    // Kiosk에 MenuService을 주입
    public Kiosk kiosk() {
        return new KioskImpl(menuService());
    }

    //MenuService에 LocalMenuItemRepository를 주입
    public MenuService menuService() {
        return new MenuServiceImpl(new LocalMenuItemRepository());
    }

}
