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

            /**
             * 메인 메뉴를 장바구니 / 주문 데이터 여부에 따라 보여줍니다.
             */
            menuService.mainMenu(orderService.orderIsEmpty());
            int menuLength = menuService.getMainMenuLength(); // 카테고리 메뉴 검증을 위한 변수
            int dynamicLength = lengthChange(menuLength); // 메뉴범위 검증을 위한 변수
            int fistInput = Input.getInput("메뉴 번호를 입력하세요: ", dynamicLength);

            // 0 입력 시 종료
            if(fistInput == 0) {
                isRun = false;
                break;
            }

            // 입력값이 1 ~ 카테고리 메뉴 길이 안에 있다면
            if (fistInput >= 1 && fistInput < menuLength) {
                mainMenuChoice(fistInput); // 카테고리 세부메뉴 보여주기
            }

            /**
             * 장바구니에 데이터가 있으면 추가메뉴 선택 가능
             *  메인메뉴길이: 4 ( 0 종료, 1 햄버거 2 음료 3 사이드 )
             *  4를 입력하면 주문메뉴, 4+1=5를 입력하면 장바구니 메뉴
             */
            if (!cartService.cartIsEmpty()) {
                if (fistInput == menuLength) {
                    orderMenu();
                } else if (fistInput == menuLength + 1) {
                    cartMenu();
                }
            } else if (!orderService.orderIsEmpty()) { // 주문 데이터가 있으면 주문조회 메뉴 추가
                if (fistInput == menuLength) {
                    orderInfo();
                }
            }

        }
    }


    /**
     * 사용자가 카테고리에서 메뉴를 선택하면 선택에 따라 메뉴 아이템목록을 보여줍니다.
     * 출력된 메뉴에서 사용자가 메뉴를 선택하면 0이 아닐 경우 해당 메뉴 데이터를 반환합니다.
     * 반환한 메뉴 데이터를 수량 선택 메서드로 넘겨 다음 과정을 진행합니다.
     * @param fistInput 사용자가 첫번째 메뉴에서 선택한 입력값
     */
    private void mainMenuChoice(int fistInput) {
        menuService.itemMenu(fistInput);
        int menuChoice = Input.getInput("메뉴 번호를 입력하세요: ", menuService.getItemMenuLength());
        if (menuChoice != 0) {
            MenuItem selectMenu = menuService.selectMenu(fistInput, menuChoice);
            quantityHandler(selectMenu);
        }
    }

    /**
     * 첫 메뉴에서 장바구니 / 주문 추가 여부에 따라 메뉴가 바뀌어 사용자가 입력할 수 있는 숫자도 달라집니다.
     * 카테고리 메뉴의 길이를 가져와 장바구니 / 주문 데이터 여부에 따라 길이를 추가하여 반환합니다.
     * 해당 메서드는 사용자가 입력할 수 있는 메뉴범위를 검증하는 데 사용됩니다.
     * @param menuLength 카테고리 메뉴의 길이
     * @return
     */
    private int lengthChange(int menuLength) {
        if(!cartService.cartIsEmpty()) {
            return menuLength + 2;
        } else if (!orderService.orderIsEmpty()) {
            return menuLength + 1;
        }
        return menuLength;
    }


    /**
     * 사용자한테 수량 데이터를 입력받고 선택한 메뉴와 함께 장바구니에 추가하는 메서드로 전달합니다.
     * @param selectMenu 사용자가 선택한 메뉴
     */
    private void quantityHandler(MenuItem selectMenu) {
        print.common.space();
        int quantity = Input.quantityInput("수량을 적어주세요: ");
        if (quantity != 0) {
            addCartHandler(selectMenu, quantity);
        }
    }

    /**
     * 사용자 입력값을 가져와 장바구니 데이터를 세팅합니다.
     * 장바구니 추가할 것인지 최종 확인을 하고 1을 입력하면 장바구니 리스트에 데이터를 추가합니다.
     * @param selectMenu 사용자가 선택한 메뉴
     * @param quantity 사용자가 선택한 수량
     */
    private void addCartHandler(MenuItem selectMenu, int quantity) {
        CartItem cartItem = cartService.setCartItem(selectMenu, quantity);
        print.cart.isAddCart(cartItem);
        int addCart = Input.getInput("메뉴 번호를 입력하세요: ", 2);
        switch (addCart) {
            case 1 -> {
                cartService.addCart(cartItem);
                System.out.println(cartItem.toString2());
                print.cart.addCart(cartItem);
            }
            case 0 -> {}
            default -> throw new IllegalArgumentException("메뉴 번호를 확인해주세요");
        }
    }

    /**
     * 장바구니 메뉴를 보여주며 현재 장바구니 상태를 출력합니다.
     * 사용자 입력에 따라 맞는 메서드(삭제, 비우기)를 실행합니다.
     */
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

    /**
     * 현재 장바구니 목록을 보여주며 Stirng 타입의 메뉴이름을 입력받습니다.
     * CartService의 삭제 메서드를 불러와 입력값을 넘깁니다.
     * 삭제 후 목록이 남아있다면 다시 장바구니 메뉴로 이동하고 비어있으면 메인메뉴로 이동합니다.
     */
    public void CartItemDelete() {
        print.cart.cartList(cartService.getCart());
        String removeItem = Input.stringInput("삭제할 메뉴 이름를 입력해주세요: ");
        cartService.removeCart(removeItem);
        if (!cartService.cartIsEmpty()) {
            cartMenu();
        }
        print.cart.cartIsEmpty();
    }


    /**
     * 주문하기 메뉴를 보여줍니다.
     * 현재 장바구니에 담긴 목록을 보여주고 합계금액을 보여줍니다.
     * 입렵값을 받아 결제 / 취소 로 넘어갑니다.
     */
    public void orderMenu() {
        print.order.orderDisplayMenu(cartService.getCart(), cartService.totalPrice());
        int select = Input.getInput("메뉴 번호를 입력하세요: ", 2);
        switch (select) {
            case 1 -> discountMenu();
            case 0 -> {}
            default -> print.common.CheckNumber();
        }
    }

    /**
     * 현재 장바구니에 담겨있는 목록으로 주문 데이터를 생성합니다.
     * 할인정책정보를 표기하고 입력값에 따라 주문 데이터에 할인율을 적용합니다.
     * 할인율이 적용된 주문 데이터를 리스트에 추가합니다.
     * 현재 담겨있는 메뉴들의 조리시간의 총합을 가져와 주문데이터의 날짜, 시간과 함께
     * 예상대기시간, 완료시기를 표기합니다.
     * 주문정보를 영수증 형태로 출력합니다.
     */
    public void discountMenu() {
        Order order = new Order(cartService.getCart());

        print.discount.discountDisplayMenu();
        int select = Input.getInput("할인 정보를 선택해주세요: ", 6);
        switch (select) {
            case 1 -> order.setDiscountPrice(Grade.NATIONAL_MERIT);
            case 2 -> order.setDiscountPrice(Grade.SOLDIER);
            case 3 -> order.setDiscountPrice(Grade.STUDENT);
            case 4 -> order.setDiscountPrice(Grade.PUBLIC);
            case 5 -> order.setDiscountPrice(Grade.GOT);
            case 0 -> {}
            default -> print.common.CheckNumber();
        }

        int delay = cartService.totalDelay();

        orderService.createOrder(order);
        print.order.receipt(orderService.findByOrder(order.getId()));
        if (order.getGrade() == Grade.GOT && findByLegend(order)) {
            delay = 0;
            print.order.easterEgg();
        } else {
            print.order.delayInfo(order, delay);
        }
    }

    /**
     * 주문번호(ID) 값을 입력받아 주문정보를 출력해 조회합니다.
     */
    public void orderInfo() {
        try {
            print.order.orderInfo();
            int orderID = Input.orderIdInput("ID: ", orderService.OrderList());
            if (orderID != 0) {
                Order order = orderService.findByOrder((long) orderID);
                print.order.receipt(order);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("주문번호가 없습니다.");
        }
    }

    // 이스터 에그
    public boolean findByLegend(Order order) {
        return order.getOrderList().stream().anyMatch(cartItem -> cartItem.getName().equals("\033[33m전설의 버거\033[0m"));
    }

}
