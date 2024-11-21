package Kiosk;

import Kiosk.menu.Category;

public interface Kiosk {

    boolean onOff = true;

    void start();

    void categoryMenu();

    void itemMenu(int categoryNumber);

    void selectMenu(int categoryNumber, int select);

    void exit();

    Category intToCategory(int categoryNumber);

    boolean getOnOff();

    void setOnOff(boolean onOff);


}
