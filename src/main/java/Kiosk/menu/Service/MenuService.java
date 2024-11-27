package Kiosk.menu.Service;

import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;

public interface MenuService {

    void categoryMenu();

    void itemMenu(int categoryNumber);

    MenuItem selectMenu(int categoryNumber, int select);

    Category intToCategory(int categoryNumber);

    int getCategoryMenuLength(); // 카테고리 메뉴 길이
    int getMainMenuLength();

}
