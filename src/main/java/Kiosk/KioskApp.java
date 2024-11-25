package Kiosk;

import Kiosk.cart.CartService;
import Kiosk.cart.CartServiceImpl;
import Kiosk.cart.CartItem;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Service.MainService;
import Kiosk.order.OrderService;
import Kiosk.order.OrderServiceImpl;

import java.util.Arrays;
import java.util.Scanner;

public class KioskApp {
    KioskConfig kioskConfig = new KioskConfig();
    CartService cart = kioskConfig.cartService();
    OrderService orderService = kioskConfig.orderService();
    MainService mainService = kioskConfig.mainService();

    boolean isRun = true; // 반복 실행 / 종료
    String[] categoryNumber = Category.numberArray(); // 입력 숫자와 카테고리 메뉴 매칭을 위한 배열
    Scanner input = new Scanner(System.in);

    void start() {
        System.out.println("주문을 시작합니다.");

        while(isRun) {
            try {
                // 카테고리 메뉴
                mainService.categoryMenu();
                String select = Input("메뉴 번호를 입력하세요: ");

                if(Arrays.asList(categoryNumber).contains(select)) {
                    mainService.itemMenu(select);
                    String select2 = Input("메뉴 번호를 입력하세요: ");
                    if(select2.equals("0")) {
                        continue;
                    } else {
                        MenuItem selectMenu = mainService.selectMenu(select, select2);
                        String quantity = Input("수량을 적어주세요: ");

                        CartItem cartItem = cart.getCartItem(selectMenu, quantity);
                        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                        System.out.println("\u001B[36m1\u001B[0m. 확인     \u001B[36m2\u001B[0m. 취소");
                        String addCart = input.next();

                        if(addCart.equals("1")) {
                            cart.addCart(cartItem);
                        }
                    }

                } else if (select.equals("4")) {
                    orderService.orderMenu();
                    continue;
                } else if(select.equals("5")) {
                    cart.cartMenu();
                    continue;
                } else if(select.equals("0")) {
                    isRun = false;
                    break;
                } else {
                    throw new IllegalArgumentException("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
                }
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private String Input(String str) {
        System.out.print(str);
        return input.nextLine();
    }


}
