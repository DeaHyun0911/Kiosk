package Kiosk.menu.Repository;

import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAll();

    List<MenuItem> findByCategoryMenu(Category category);

    MenuItem findByMenu(Category categoryNumber, int menuNumber);
}
