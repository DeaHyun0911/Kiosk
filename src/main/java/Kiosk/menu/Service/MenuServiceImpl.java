package Kiosk.menu.Service;

import Kiosk.cart.CartService;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Repository.MenuItemRepository;
import Kiosk.utils.PrintService;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    private final MenuItemRepository menuItemRepository;
    private final CartService cartService;
    private final PrintService print;

    private int categoryMenuLength = 0;
    private int mainMenuLength = 0;

    public MenuServiceImpl(MenuItemRepository menuItemRepository, CartService cartService, PrintService printService) {
        this.menuItemRepository = menuItemRepository;
        this.cartService = cartService;
        this.print = printService;
    }

    // 카테고리 메뉴
    @Override
    public void categoryMenu() {
        Category[] categories = Category.values();
        print.fistMenu.title();
        print.fistMenu.menu(categories);
        if(!cartService.getCart().isEmpty()) {
            print.fistMenu.addMenu();
        }
        print.fistMenu.exit();
        this.categoryMenuLength = categories.length + 3;
    }

    // 세부 메뉴 출력
    @Override
    public void itemMenu(int categoryNumber) {
        Category category = intToCategory(categoryNumber);
        List<MenuItem> itemMenu = menuItemRepository.findByCategoryMenu(category);
        System.out.println();
        System.out.println("\033[38;5;214m[ " + category + " MENU ]\033[0m");
        for(int i = 0; i < itemMenu.size(); i++) {
            System.out.println("\u001B[36m" + (i+1) + "\u001B[0m. " + itemMenu.get(i).getName() + " | " + itemMenu.get(i).getPrice() + "원 " + " | " + itemMenu.get(i).getDescription());
        }
        System.out.println("\u001B[36m0.\u001B[0m 뒤로가기");

        this.mainMenuLength = itemMenu.size() + 1;
    }


    // 선택한 메뉴 출력
    @Override
    public MenuItem selectMenu(int categoryNumber, int select) {
        Category category = intToCategory(categoryNumber);
        MenuItem selectMenu = menuItemRepository.findByMenu(category, select);
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

    @Override
    public int getCategoryMenuLength() {
        return categoryMenuLength;
    }

    @Override
    public int getMainMenuLength() {
        return mainMenuLength;
    }


}
