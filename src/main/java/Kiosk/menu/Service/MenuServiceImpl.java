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

    private int mainMenuLength = 0;
    private int itemMenuLength = 0;

    public MenuServiceImpl(MenuItemRepository menuItemRepository, CartService cartService, PrintService printService) {
        this.menuItemRepository = menuItemRepository;
        this.cartService = cartService;
        this.print = printService;
    }

    // 카테고리 메뉴
    @Override
    public void mainMenu() {
        Category[] categories = Category.values();
        boolean hasCartItems = !cartService.getCart().isEmpty();
        print.main.menus(categories, hasCartItems);
        this.mainMenuLength = categories.length + 3;
    }

    // 아이템 메뉴
    @Override
    public void itemMenu(int categoryNumber) {
        Category category = intToCategory(categoryNumber);
        List<MenuItem> itemMenu = menuItemRepository.findByCategoryMenu(category);
        print.item.menus(category, itemMenu);
        this.itemMenuLength = itemMenu.size() + 1;
    }


    // 선택한 메뉴
    @Override
    public MenuItem selectMenu(int categoryNumber, int select) {
        Category category = intToCategory(categoryNumber);
        MenuItem selectMenu = menuItemRepository.findByMenu(category, select);
        print.item.select(selectMenu);
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
    public int getMainMenuLength() {
        return mainMenuLength;
    }

    @Override
    public int getItemMenuLength() {
        return itemMenuLength;
    }


}
