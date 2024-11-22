package Kiosk.menu.Repository;

import Kiosk.menu.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAll();
}
