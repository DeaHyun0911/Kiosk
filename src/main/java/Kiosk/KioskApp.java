package Kiosk;

import Kiosk.cart.CartService;
import Kiosk.cart.CartItem;
import Kiosk.discount.Grade;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Service.MenuService;
import Kiosk.order.Order;
import Kiosk.order.repository.OrderRepository;
import Kiosk.order.service.OrderService;
import Kiosk.utils.InputService;

public class KioskApp {
    KioskConfig kioskConfig = new KioskConfig();
    CartService cartService = kioskConfig.cartService();
    OrderService orderService = kioskConfig.orderService();
    OrderRepository orderRepository = kioskConfig.orderRepository();
    MenuService menuService = kioskConfig.menuService();
    InputService input1 = kioskConfig.inputService();

    boolean isRun = true; // 반복 실행 / 종료

    void start() {
        System.out.println("주문을 시작합니다.");

        while(isRun) {
                // 카테고리 메뉴
                menuService.categoryMenu();
                int menuLength = menuService.getCategoryMenuLength();
                int fistInput = input1.getInput("메뉴 번호를 입력하세요: ", menuLength);
                if(fistInput <= menuLength - 3) {
                    CategoryMenuChoice(fistInput); // 카테고리 세부메뉴 보여주기
                } else if (fistInput == menuLength - 2) { // 주문 메뉴
                    orderMenu();
                } else if(fistInput == menuLength - 1) { // 장바구니 메뉴
                    cartMenu();
                } else if (fistInput == 0) {
                    isRun = false;
                    break;
                }
        }
    }


    // 카테고리 메뉴 선택에 따라 메인메뉴 보여주고 선택하는 메서드
    private void CategoryMenuChoice(int fistInput) {
                menuService.itemMenu(fistInput);
                int menuChoice = input1.getInput("메뉴 번호를 입력하세요: ", menuService.getMainMenuLength());
                if (menuChoice != 0) {
                    MenuItem selectMenu = menuService.selectMenu(fistInput, menuChoice);
                    quantityHandler(selectMenu);
                }
    }


    // 수량 선택 메서드
    private void quantityHandler(MenuItem selectMenu) {
                System.out.println();
                int quantity = input1.getQuantityInput("수량을 적어주세요: ");
                if(quantity != 0) {
                    addCartHandler(selectMenu, quantity);
                }
    }

    // 장바구니에 메뉴 추가
    private void addCartHandler(MenuItem selectMenu, int quantity) {
                CartItem cartItem = cartService.getCartItem(selectMenu, quantity);
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("\u001B[36m1\u001B[0m. 확인     \u001B[36m0\u001B[0m. 취소");

                int addCart = input1.getInput("메뉴 번호를 입력하세요: ", 2);
                switch (addCart) {
                    case 1 -> cartService.addCart(cartItem);
                    case 0 -> { return; }
                    default -> throw new IllegalArgumentException("메뉴 번호를 확인해주세요");
                }
    }

    // 장바구니 메뉴 메서드
    public void cartMenu() {
        System.out.println();
        System.out.println("\033[38;5;214m[ CART MENU ]\033[0m");
        cartService.cartList();
        System.out.println("합계: " + "\u001B[36m" + cartService.totalPrice() + "\u001B[0m" + "원");
        System.out.println("\u001B[36m1\u001B[0m. 삭제하기    \u001B[36m2\u001B[0m. 비우기    \u001B[36m3\u001B[0m. 메인으로 돌아가기");
        int select = input1.getInput("메뉴 번호를 입력하세요: ", 3);
        switch (select) {
            case 1:
                System.out.println();
                for (int i = 0; i < cartService.getCart().size(); i++) {
                    System.out.println(cartService.getCart().get(i));
                }
                String removeMenu = input1.stringInput("삭제할 메뉴 이름를 입력해주세요: ");
                CartItem item = cartService.getCart().stream().filter(r -> r.getName().equals(removeMenu)).findFirst().orElseThrow();
                cartService.getCart().remove(item);
                if(!cartService.cartIsEmpty()) {
                    cartMenu();
                }
                System.out.println("장바구니가 비었습니다.");
                break;
            case 2:
                cartService.getCart().clear();
                System.out.println("장바구니가 비었습니다.");
                break;
            case 3:
                System.out.println();
                break;
            default:
                System.out.println("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
        }

    }


    public void orderMenu() {
        System.out.println();
        System.out.println("\033[38;5;214m[ ORDERS ]\033[0m");
        cartService.cartList();
        System.out.println("합계: " + "\u001B[36m" + cartService.totalPrice() + "\u001B[0m" + "원");
        System.out.println("\u001B[36m1\u001B[0m. 결제하기    \u001B[36m2\u001B[0m. 취소");
        int select = input1.getInput("메뉴 번호를 입력하세요: ", 2);
        switch (select) {
            case 1 -> discountMenu();
            case 0 -> { return; }
            default -> System.out.println("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
        }
    }

    public void discountMenu() {
        Long discountPrice = 0L;
        System.out.println();
        System.out.println("\033[38;5;214m[ DISCOUNT ]\033[0m");

        Grade[] grades = Grade.values();
        for(int i = 0; i < grades.length; i++) {
            System.out.println("\u001B[36m" + (i + 1) + "\u001B[0m. " + grades[i].title + " : " + grades[i].percent + "%");
        }

        boolean flag = true;

        while (flag) {
            try {
                int select = input1.getInput("할인 정보를 선택해주세요: ", 4);

                switch (select) {
                    case 1:
                        discountPrice = cartService.totalPrice() * Grade.NATIONAL_MERIT.percent / 100;
                        flag = false;
                        break;
                    case 2:
                        discountPrice = cartService.totalPrice() * Grade.SOLDIER.percent / 100;
                        flag = false;
                        break;
                    case 3:
                        discountPrice = cartService.totalPrice() * Grade.STUDENT.percent / 100;
                        flag = false;
                        break;
                    case 4:
                        flag = false;
                        break;
                    default:
                        System.out.println("\u001B[31m메뉴 번호를 확인해주세요!\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31m올바른 숫자를 입력해주세요!\u001B[0m");
                System.out.println();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        Order order = new Order(cartService.getCart(), discountPrice);
        orderRepository.saveOrder(order);
        cartService.cartClear();

        System.out.println("주문이 완료되었습니다. ");
        printOrderInfo(order.getId());
    }

    public void printOrderInfo(Long orderId) {
        Order order = orderRepository.findByOrder(orderId);

        System.out.println("\033[1;34m+----------------------------------+\033[0m");
        System.out.println("\033[1;34m          주문번호 : " + orderId + "        \033[0m");
        System.out.println("\033[1;34m+----------------------------------+\033[0m");
        System.out.println(String.format("%-15s %5s %10s", "상품명", "수량", "가격"));
        order.getOrderList().forEach(item ->
                System.out.printf("%-15s %5s %10s%n", item.getName(), item.getQuantity(), item.getPrice())
        );
        System.out.println("\033[1;34m+----------------------------------+\033[0m");
        System.out.printf("%-15s %15d원%n", "합계금액:", order.getPrice());
        System.out.printf("%-15s %15d원%n", "할인금액:", -order.getDiscountPrice());
        System.out.printf("%-15s %15d원%n", "결제금액:", order.getTotalPrice());
        System.out.println("\033[1;34m+----------------------------------+\033[0m");
    }

}
