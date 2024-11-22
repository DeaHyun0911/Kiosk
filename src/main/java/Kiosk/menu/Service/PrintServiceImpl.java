package Kiosk.menu.Service;

import Kiosk.KioskConfig;
import Kiosk.cart.Cart;
import Kiosk.cart.CartItem;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;

import java.util.List;

public class PrintServiceImpl implements PrintService {
    KioskConfig kioskConfig = new KioskConfig();  // 키오스크 실행 / 종료

    MenuService menuService;
    Cart cart;

    public PrintServiceImpl(MenuService menuService, Cart cart) {     // 생성자를 통해 menuService를 주입받음
        this.menuService = menuService;
        this.cart = cart;
    }

    // 카테고리 메뉴 출력
    @Override
    public void categoryMenu() {
        Category[] categories = Category.values();
        System.out.println();
        System.out.println("\u001B[32m[ MOM'S TOUCH MENU ]\u001B[0m.");
        for(int i = 0; i < categories.length; i++) {
            System.out.print("\u001B[34m" + (i + 1) + "\u001B[0m. " + categories[i] + "    ");
        }
        if(!cart.getCart().isEmpty()) {
            System.out.print("\u001B[34m4\u001B[0m. 주문하기    \u001B[34m5\u001B[0m. 장바구니    ");
        }
        System.out.println("\u001B[34m0.\u001B[0m 종료");
    }

    // 세부 메뉴 출력
    @Override
    public void itemMenu(String categoryNumber) {
        Category category = intToCategory(Integer.parseInt(categoryNumber));
        List<MenuItem> itemMenu = menuService.findByCategoryMenu(category);
        System.out.println("");
        System.out.println("\u001B[32m[ " + category + " MENU ]\u001B[0m.");
        for(int i = 0; i < itemMenu.size(); i++) {
            System.out.println("\u001B[34m" + (i+1) + "\u001B[0m. " + itemMenu.get(i).getName() + " | " + itemMenu.get(i).getPrice() + "원 " + " | " + itemMenu.get(i).getDescription());
        }
        System.out.println("\u001B[34m0.\u001B[0m 뒤로가기");
    }

    // 선택한 메뉴 출력
    @Override
    public MenuItem selectMenu(String categoryNumber, String select) {
        Category category = intToCategory(Integer.parseInt(categoryNumber));
        MenuItem selectMenu = menuService.findByMenu(category, Integer.parseInt(select));
        System.out.println();
        System.out.println("\u001B[34m선택한 메뉴\u001B[0m: " + selectMenu.getName() + " | " + selectMenu.getPrice() + "원" + " | " + selectMenu.getDescription());
        return selectMenu;
    }

    // 선택한 숫자를 카테고리 타입으로 변경
    @Override
    public Category intToCategory(int categoryNumber) {
        return Category.values()[categoryNumber - 1];
    }

}
