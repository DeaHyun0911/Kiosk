package Kiosk.menu;

import java.util.List;

public interface MenuService {


    List<MenuItem> findByCategoryMenu(Category category);

    MenuItem findByMenu(Category categoryNumber, int menuNumber);
}
