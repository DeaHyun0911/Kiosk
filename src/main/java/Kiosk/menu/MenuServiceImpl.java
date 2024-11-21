package Kiosk.menu;

import Kiosk.KioskConfig;

import java.util.List;

public class MenuServiceImpl implements MenuService {
    KioskConfig kioskConfig = new KioskConfig();

    // MenuServiceImpl이 MenuItemRepository 인터페이스에 의존
    private final MenuItemRepository menuItemRepository;

    // 생성자를 통해 menuItemRepository를 주입받음
    public MenuServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    // 선택한 카테고리 메뉴 리스트 반환
    @Override
    public List<MenuItem> findByCategoryMenu(Category category) {
            return menuItemRepository.findByCategory(category);
    }

    // 선택한 메뉴 리스트 반한
    @Override
    public MenuItem findByMenu(Category categoryNumber, int menuNumber) {
        return menuItemRepository.findByMenu(categoryNumber, menuNumber);
    }
}
