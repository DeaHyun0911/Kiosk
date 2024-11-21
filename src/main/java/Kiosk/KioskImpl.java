package Kiosk;

import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;
import Kiosk.menu.MenuService;
import Kiosk.menu.MenuServiceImpl;

import java.util.InputMismatchException;
import java.util.List;

public class KioskImpl implements Kiosk {

    KioskConfig kioskConfig = new KioskConfig();
    private boolean onOff = true; // 키오스크 실행 / 종료

    // KioskImpl이 MenuService 인터페이스에 의존
    MenuService menuService;
    // 생성자를 통해 menuService를 주입받음
    public KioskImpl(MenuService menuService) {
        this.menuService = menuService;
    }

    // 키오스크 실행
    @Override
    public void start() {
        System.out.println("주문을 시작합니다.");
        setOnOff(true);
    }

    // 카테고리 메뉴 출력
    @Override
    public void categoryMenu() {
        Category[] categories = Category.values();
        System.out.println("[ MOM'S TOUCH MENU ]");
        for(int i = 0; i < categories.length; i++) {
            System.out.println(i + 1 + ". " + categories[i]);
        }
        System.out.println("0. 종료");
    }

    // 세부 메뉴 출력
    @Override
    public void itemMenu(int categoryNumber) {
        Category category = intToCategory(categoryNumber);
        List<MenuItem> itemMenu = menuService.findByCategoryMenu(category);
        System.out.println("[ " + category + " MENU ]");
        for(int i = 0; i < itemMenu.size(); i++) {
            System.out.println(i+1 + ". " + itemMenu.get(i).getName() + " | " + itemMenu.get(i).getPrice() + "원 " + " | " + itemMenu.get(i).getDescription());
        }
        System.out.println("0. 뒤로가기");
    }

    // 선택한 메뉴 출력
    @Override
    public void selectMenu(int categoryNumber, int select) {
        Category category = intToCategory(categoryNumber);
        MenuItem selectMenu = menuService.findByMenu(category, select);
        System.out.println("선택한 메뉴: " + selectMenu.getName() + " | " + selectMenu.getPrice() + "원 " + " | " + selectMenu.getDescription());
    }

    // 키오스크 종료
    @Override
    public void exit() {
        System.out.println("주문을 종료합니다.");
        setOnOff(false);
    }

    // 선택한 숫자를 카테고리 타입으로 변경
    @Override
    public Category intToCategory(int categoryNumber) {
        return Category.values()[categoryNumber - 1];
    }

    public boolean getOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
            this.onOff = true;
    }
}
