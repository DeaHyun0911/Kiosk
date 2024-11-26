package Kiosk.menu.Service;

import Kiosk.KioskConfig;
import Kiosk.cart.CartService;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Repository.MenuItemRepository;

import java.util.List;

public class MainServiceImpl implements MainService {

    private final MenuItemRepository menuItemRepository;
    private final CartService cartService;

    public MainServiceImpl(MenuItemRepository menuItemRepository, CartService cartService) {     // 생성자를 통해 menuService를 주입받음
        this.menuItemRepository = menuItemRepository;
        this.cartService = cartService;
    }

    // 카테고리 메뉴 출력
    @Override
    public void categoryMenu() {
        Category[] categories = Category.values();
        System.out.println();
        System.out.println("\033[38;5;214m[ MOM'S TOUCH MENU ]\033[0m");
        for(int i = 0; i < categories.length; i++) {
            System.out.print("\u001B[36m" + (i + 1) + "\u001B[0m. " + categories[i] + "    ");
        }
        if(!cartService.getCart().isEmpty()) {
            System.out.print("\u001B[36m4\u001B[0m. ORDERS   \u001B[36m5\u001B[0m. CART    ");
        }
        System.out.println("\u001B[36m0.\u001B[0m EXIT");
    }

    // 세부 메뉴 출력
    @Override
    public int itemMenu(String categoryNumber) {
        Category category = intToCategory(Integer.parseInt(categoryNumber));
        List<MenuItem> itemMenu = menuItemRepository.findByCategoryMenu(category);
        System.out.println("");
        System.out.println("\033[38;5;214m[ " + category + " MENU ]\033[0m");
        for(int i = 0; i < itemMenu.size(); i++) {
            System.out.println("\u001B[36m" + (i+1) + "\u001B[0m. " + itemMenu.get(i).getName() + " | " + itemMenu.get(i).getPrice() + "원 " + " | " + itemMenu.get(i).getDescription());
        }
        System.out.println("\u001B[36m0.\u001B[0m 뒤로가기");

        return itemMenu.size();
    }


    // 선택한 메뉴 출력
    @Override
    public MenuItem selectMenu(String categoryNumber, String select) {
        Category category = intToCategory(Integer.parseInt(categoryNumber));
        MenuItem selectMenu = menuItemRepository.findByMenu(category, Integer.parseInt(select));
        System.out.println();
        System.out.println("\u001B[36m선택한 메뉴\u001B[0m: " + selectMenu.getName() + " | " + selectMenu.getPrice() + "원" + " | " + selectMenu.getDescription());
        return selectMenu;
    }

    // 선택한 숫자를 카테고리 타입으로 변경
    @Override
    public Category intToCategory(int categoryNumber) {
        Category[] categories = Category.values();
        if (categoryNumber < 1 || categoryNumber > categories.length) {
            throw new IllegalArgumentException("유효하지 않은 카테고리 번호: " + categoryNumber);
        }
        return categories[categoryNumber - 1];
    }

}
