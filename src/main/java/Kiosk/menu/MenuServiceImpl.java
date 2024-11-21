package Kiosk.menu;

import java.util.List;

public class MenuServiceImpl implements MenuService {
    private final MenuItemRepository menuItemRepository = new LocalMenuItemRepository();

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
