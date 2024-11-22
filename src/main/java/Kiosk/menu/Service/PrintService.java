package Kiosk.menu.Service;

import Kiosk.cart.CartItem;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;

public interface PrintService {

    void categoryMenu();

    void itemMenu(String categoryNumber);

    MenuItem selectMenu(String categoryNumber, String select);

    Category intToCategory(int categoryNumber);

}
