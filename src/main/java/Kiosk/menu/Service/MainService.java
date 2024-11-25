package Kiosk.menu.Service;

import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;

public interface MainService {

    void categoryMenu();

    void itemMenu(String categoryNumber);

    MenuItem selectMenu(String categoryNumber, String select);

    Category intToCategory(int categoryNumber);

}
