package Kiosk.menu.Service;

import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;

public interface MenuService {

    void mainMenu();

    void itemMenu(int categoryNumber);

    MenuItem selectMenu(int categoryNumber, int select);

    Category intToCategory(int categoryNumber);

    int getMainMenuLength(); // 카테고리 메뉴 길이
    int getItemMenuLength();

}
