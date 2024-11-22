package Kiosk.menu.Service;

import Kiosk.KioskConfig;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Repository.MenuItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MenuServiceImpl implements MenuService {
    KioskConfig kioskConfig = new KioskConfig();

    private final MenuItemRepository menuItemRepository; // MenuServiceImpl이 MenuItemRepository 인터페이스에 의존

    public MenuServiceImpl(MenuItemRepository menuItemRepository) { // 생성자를 통해 menuItemRepository를 주입받음
        this.menuItemRepository = menuItemRepository;
    }

    // 선택한 카테고리 메뉴 리스트 반환
    @Override
    public List<MenuItem> findByCategoryMenu(Category category) {
        return menuItemRepository.findAll().stream()
                .filter(item -> item.getCategory() == category)
                .collect(Collectors.toList());
    }

    // 선택한 메뉴 리스트 반한
    @Override
    public MenuItem findByMenu(Category categoryNumber, int menuNumber) {
        return findByCategoryMenu(categoryNumber).get(menuNumber - 1);
    }

}
