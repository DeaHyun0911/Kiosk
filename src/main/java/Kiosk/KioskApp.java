package Kiosk;

import Kiosk.cart.CartService;
import Kiosk.cart.CartItem;
import Kiosk.discount.Grade;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Service.MenuService;
import Kiosk.order.Order;
import Kiosk.order.service.OrderService;
import Kiosk.utils.InputService;
import Kiosk.utils.PrintService;

public class KioskApp {
    KioskConfig kioskConfig = new KioskConfig();
    CartService cartService = kioskConfig.cartService();
    OrderService orderService = kioskConfig.orderService();
    MenuService menuService = kioskConfig.menuService();
    InputService Input = kioskConfig.inputService();
    PrintService print = kioskConfig.printService();

    boolean isRun = true; // 반복 실행 / 종료

    void start() {
        System.out.println("주문을 시작합니다.");

        while (isRun) {
            // 메인 메뉴
            menuService.mainMenu();
            int menuLength = menuService.getMainMenuLength();
            int fistInput = Input.getInput("메뉴 번호를 입력하세요: ", (cartService.cartIsEmpty()) ? menuLength : menuLength + 2);

            // 0 입력 시 종료
            if(fistInput == 0) {
                isRun = false;
                break;
            }

            // 카테고리 메뉴
            if (fistInput >= 1 && fistInput < menuLength) {
                mainMenuChoice(fistInput); // 카테고리 세부메뉴 보여주기
            }

            // 장바구니에 데이터가 있으면 추가메뉴 선택 가능
            if (!cartService.cartIsEmpty()) {
                if (fistInput == menuLength) {
                    orderMenu();
                } else if (fistInput == menuLength + 1) {
                    cartMenu();
                }
            }

        }
    }


    // 카테고리 메뉴 선택에 따라 메인메뉴 보여주고 선택하는 메서드
    private void mainMenuChoice(int fistInput) {
        menuService.itemMenu(fistInput);
        int menuChoice = Input.getInput("메뉴 번호를 입력하세요: ", menuService.getItemMenuLength());
        if (menuChoice != 0) {
            MenuItem selectMenu = menuService.selectMenu(fistInput, menuChoice);
            quantityHandler(selectMenu);
        }
    }


    // 수량 선택 메서드
    private void quantityHandler(MenuItem selectMenu) {
        print.common.space();
        int quantity = Input.getQuantityInput("수량을 적어주세요: ");
        if (quantity != 0) {
            addCartHandler(selectMenu, quantity);
        }
    }

    // 장바구니에 메뉴 추가
    private void addCartHandler(MenuItem selectMenu, int quantity) {
        CartItem cartItem = cartService.setCartItem(selectMenu, quantity);
        print.cart.isAddCart(cartItem);
        int addCart = Input.getInput("메뉴 번호를 입력하세요: ", 2);
        switch (addCart) {
            case 1 -> {
                cartService.addCart(cartItem);
                print.cart.addCart(cartItem);
            }
            case 0 -> {}
            default -> throw new IllegalArgumentException("메뉴 번호를 확인해주세요");
        }
    }

    // 장바구니 메뉴 보여주는 메서드
    public void cartMenu() {
        print.cart.cartDisplayMenu(cartService.getCart(), cartService.totalPrice());
        int select = Input.getInput("메뉴 번호를 입력하세요: ", 3);
        switch (select) {
            case 1 -> CartItemDelete();
            case 2 -> cartService.getCart().clear();
            case 0 -> {}
            default -> print.common.CheckNumber();
        }
    }

    // 장바구니에서 메뉴 삭제 하기
    public void CartItemDelete() {
        print.cart.cartList(cartService.getCart());
        String removeItem = Input.stringInput("삭제할 메뉴 이름를 입력해주세요: ");
        cartService.removeCart(removeItem);
        if (!cartService.cartIsEmpty()) {
            cartMenu();
        }
        print.cart.cartIsEmpty();
    }


    // 주문하기 메뉴 보여주는 메서드
    public void orderMenu() {
        print.order.orderDisplayMenu(cartService.getCart(), cartService.totalPrice());
        int select = Input.getInput("메뉴 번호를 입력하세요: ", 2);
        switch (select) {
            case 1 -> discountMenu();
            case 0 -> {}
            default -> print.common.CheckNumber();
        }
    }

    // 할인정책 선택 후 주문 완료하는 메서드
    public void discountMenu() {
        Order order = new Order(cartService.getCart());

        print.discount.discountDisplayMenu();
        int select = Input.getInput("할인 정보를 선택해주세요: ", 4);
        switch (select) {
            case 1 -> order.setDiscountPrice(cartService.totalPrice() * Grade.NATIONAL_MERIT.percent / 100);
            case 2 -> order.setDiscountPrice(cartService.totalPrice() * Grade.SOLDIER.percent / 100);
            case 3 -> order.setDiscountPrice(cartService.totalPrice() * Grade.STUDENT.percent / 100);
            case 4 -> {
                return;
            }
            default -> print.common.CheckNumber();
        }

        orderService.createOrder(order);
        print.order.receipt(orderService.findByOrder(order.getId()));
    }

}
