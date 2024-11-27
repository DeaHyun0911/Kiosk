package Kiosk.menu.Repository;


import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocalMenuItemRepository implements MenuItemRepository {

    private List<MenuItem> menu = new ArrayList<>(List.of(
            new MenuItem(1L, Category.BURGERS, 1, "싸이버거", 6000, "바삭하고 매콤한 치킨 패티와 신선한 양상추가 조화를 이루는 시그니처 버거", 10),
            new MenuItem(2L, Category.BURGERS, 2, "딥치즈버거", 8000, "부드러운 치즈와 한층 더 촉촉해진 통가슴살 패티가 일품인 버거", 10),
            new MenuItem(3L, Category.BURGERS, 3, "불고기버거", 9000, "영양 만점 불고기 패티에 고소한 불고기 소소를 더해 한층 더 맛있는 버거", 10),
            new MenuItem(4L, Category.BURGERS, 4, "언빌리버블버거", 10000, "통새우, 에그프라이, 통가슴살 패티에 매콤한 스리라차 어쩌고 저쩌고..생략", 15),
            new MenuItem(5L, Category.BURGERS, 5, "\033[33m전설의 버거\033[0m", 999999, "전설로만 전해지는 집게사장이 만든 레전드 버거", 525600),

            new MenuItem(6L, Category.DRINKS, 1, "콜라", 1000, "시원한 콜라 한잔!", 0),
            new MenuItem(7L, Category.DRINKS,2, "사이다", 1000, "톡 쏘는 사이다 한잔!", 0),
            new MenuItem(8L, Category.DRINKS,3, "아메리카노", 1500, "아메 아메 아메 아메리카노~~ 좋아 좋아", 3),

            new MenuItem(9L, Category.SIDES,1, "코울슬로", 1500, "아삭한 식감과 상큼한 맛의 코울슬로", 0),
            new MenuItem(10L, Category.SIDES,2, "바삭크림치즈볼", 2500, "바삭하고 쫀득하게 즐기는 치즈볼(4조각)", 5),
            new MenuItem(11L, Category.SIDES,3, "케이준앙념감자", 2000, "케이준스타일의 바삭한 양념감자", 5),
            new MenuItem(12L, Category.SIDES,4, "치즈스틱", 2000, "고단백 영양 만점의 모짜렐라 치즈스틱", 5)
    ));

    @Override
    public List<MenuItem> findAll() {
        return new ArrayList<>(menu);
    }

    public List<MenuItem> findByCategoryMenu(Category category) {
        return menu.stream()
                .filter(item -> item.getCategory() == category)
                .collect(Collectors.toList());
    }

    public MenuItem findByMenu(Category categoryNumber, int menuNumber) {
        return findByCategoryMenu(categoryNumber).get(menuNumber - 1);
    }
}
