package Kiosk;

import Kiosk.menu.Category;
import Kiosk.menu.LocalMenuItemRepository;
import Kiosk.menu.MenuItemRepository;

import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        Kiosk kiosk = new KioskImpl();
        Category[] categories = Category.values();
        MenuItemRepository menuItemRepository = new LocalMenuItemRepository();
        Scanner scanner = new Scanner(System.in);

        kiosk.start();

        System.out.println("[ MOM'S TOUCH MENU ]");
        for(int i = 0; i < categories.length; i++) {
            System.out.println(i + 1 + ". " + categories[i]);
        }



    }
}