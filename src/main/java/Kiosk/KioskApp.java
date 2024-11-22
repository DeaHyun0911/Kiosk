package Kiosk;

import Kiosk.cart.Cart;
import Kiosk.cart.CartImpl;
import Kiosk.cart.CartItem;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Service.PrintService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KioskApp {
    KioskConfig kioskConfig = new KioskConfig();
    Cart cart = new CartImpl();
    PrintService printService = kioskConfig.printService(cart);
    boolean isRun = true;

    Scanner input = new Scanner(System.in);

    void start() {
        System.out.println("주문을 시작합니다.");

        while(isRun) {

            printService.categoryMenu();
            String select = input.next();
            if(select.equals("5")) {
                cart.cartMenu();
                continue;
            } else if(select.equals("6")) {
                isRun = false;
                break;
            }
            printService.itemMenu(select);
            String select2 = input.next();
            MenuItem selectMenu = printService.selectMenu(select, select2);
            System.out.print("수량을 적어주세요.");
            String quantity = input.next();
            CartItem cartItem = cart.getCartItem(selectMenu, quantity);
            System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
            System.out.println("\u001B[34m1\u001B[0m. 확인     \u001B[34m2\u001B[0m. 취소");
            String addCart = input.next();
            if(addCart.equals("1")) {
                cart.addCart(cartItem);
            }
        }
    }
}
