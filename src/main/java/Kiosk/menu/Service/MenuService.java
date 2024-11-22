package Kiosk.menu.Service;

import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;

import java.util.List;

public interface MenuService {

    List<MenuItem> findByCategoryMenu(Category category);

    MenuItem findByMenu(Category categoryNumber, int menuNumber);
}
