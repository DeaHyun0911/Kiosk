package Kiosk.menu.Service;

import Kiosk.cart.CartService;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Repository.MenuItemRepository;
import Kiosk.order.service.OrderService;
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

    /**
     * 현재 카테고리 타입을 배열로 만들어
     * 메뉴 출력 메서드로 카테고리, 장바구니 데이터 여부, 주문 데이터 여부를 전달합니다.
     * 데이터에 따라 알맞는 메뉴를 출력합니다.
     * 카테고리 길이를 현재 메뉴 길이로 설정합니다.
     * @param orderIsEmpty 주문 데이터 여부
     */
    @Override
    public void mainMenu(boolean orderIsEmpty) {
        Category[] categories = Category.values();
        boolean hasCartItems = !cartService.getCart().isEmpty();
        print.main.menus(categories, hasCartItems, orderIsEmpty);
        this.mainMenuLength = categories.length + 1;
    }

    /**
     * 받은 값은 카테고리 타입으로 변환하여 저장소에서 메뉴 리스트를 찾아옵니다.
     * 받아온 메뉴 리스트를 출력 메서드로 전달해 메뉴를 출력합니다.
     * 메뉴길이를 사용자가 선택한 메뉴길이로 설정합니다.
     * @param categoryNumber 선택한 카테고리
     */
    @Override
    public void itemMenu(int categoryNumber) {
        Category category = intToCategory(categoryNumber);
        List<MenuItem> itemMenu = menuItemRepository.findByCategoryMenu(category);
        print.item.menus(category, itemMenu);
        this.itemMenuLength = itemMenu.size() + 1;
    }


    /**
     * 사용자가 입력한 값에 따라 저장소에서 메뉴 데이터를 찾아옵니다.
     * 찾아온 메뉴 데이터를 출력하고 반환합니다.
     * @param categoryNumber 선택한 카테고리
     * @param select 선택한 메뉴
     * @return
     */
    @Override
    public MenuItem selectMenu(int categoryNumber, int select) {
        Category category = intToCategory(categoryNumber);
        MenuItem selectMenu = menuItemRepository.findByMenu(category, select);
        print.item.select(selectMenu);
        return selectMenu;
    }

    /**
     * 숫자 타입으로 받은 변수를 저장소에서 필터링 할 수 있도록
     * 카테고리 타입으로 변경하여 반환합니다.
     * @param categoryNumber 선택한 카테고리
     * @return
     */
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
