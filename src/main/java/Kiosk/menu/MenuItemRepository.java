package Kiosk.menu;

import java.util.List;

public interface MenuItemRepository {

    MenuItem findByMenu(Category category, int selectId);

    List<MenuItem> findByCategory(Category category);
}
