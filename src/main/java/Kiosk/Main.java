package Kiosk;

import Kiosk.menu.Category;
import Kiosk.menu.LocalMenuItemRepository;
import Kiosk.menu.MenuItemRepository;
import Kiosk.menu.MenuService;

import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        KioskConfig config = new KioskConfig();

        // 키오스크 객체 주입 받음
        Kiosk kiosk = config.kiosk();
        Scanner scanner = new Scanner(System.in);

        kiosk.start();

        // 메뉴 반복 시작
        while(kiosk.getOnOff()) {

            // 카테고리 선택 시작
            kiosk.categoryMenu();

            // 카테고리 번호 선택
            int categoryNumber = scanner.nextInt();
            if (categoryNumber == 0) {
                // 0 선택 시 종료
                kiosk.exit();
                break;
            } else {
                // 선택한 메뉴 출력
                kiosk.itemMenu(categoryNumber);
            }
            // 세부 메뉴 선택
            int menuNumber = scanner.nextInt();
            if (menuNumber== 0) {
                // 0 선택 시 이전으로 돌아가기
                continue;
            } else {
                // 선택한 메뉴 출력
                kiosk.selectMenu(categoryNumber, menuNumber);
            }
        }
    }
}