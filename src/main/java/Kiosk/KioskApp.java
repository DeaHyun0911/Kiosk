package Kiosk;

import Kiosk.cart.CartService;
import Kiosk.cart.CartItem;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;
import Kiosk.menu.Service.MainService;
import Kiosk.order.Order;
import Kiosk.order.service.OrderService;

import java.util.Arrays;
import java.util.Scanner;

public class KioskApp {
    KioskConfig kioskConfig = new KioskConfig();
    CartService cart = kioskConfig.cartService();
    OrderService orderService = kioskConfig.orderService();
    MainService mainService = kioskConfig.mainService();

    boolean isRun = true; // 반복 실행 / 종료
    String[] categoryListNumber = Category.numberArray(); // 입력 숫자와 카테고리 메뉴 매칭을 위한 배열
    Scanner input = new Scanner(System.in);

    void start() {
        System.out.println("주문을 시작합니다.");

        while(isRun) {
            try {
                // 카테고리 메뉴
                mainService.categoryMenu();
                String fistInput = Input("메뉴 번호를 입력하세요: ");
                if(Arrays.asList(categoryListNumber).contains(fistInput)) {
                    CategoryMenuChoice(fistInput); // 카테고리 세부메뉴 보여주기
                } else if (fistInput.equals("4")) { // 주문 메뉴
                    orderMenu();
                    continue;
                } else if(fistInput.equals("5")) { // 장바구니 메뉴
                    cartMenu();
                    continue;
                } else if (fistInput.equals("0")) {
                    isRun = false;
                    break;
                } else {
                    throw new IllegalArgumentException("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    // 사용자 입력 메서드
    private String Input(String str) {
        System.out.print(str);
        return input.nextLine();
    }


    // 카테고리 메뉴 선택에 따라 메인메뉴 보여주고 선택하는 메서드
    private void CategoryMenuChoice(String fistInput) {
        while (true) {
            try {
                int menuLength = mainService.itemMenu(fistInput);
                String menuChoice = Input("메뉴 번호를 입력하세요: ");
                if (Integer.parseInt(menuChoice) <= menuLength && !menuChoice.equals("0")) {
                    MenuItem selectMenu = mainService.selectMenu(fistInput, menuChoice);
                    quantityHandler(selectMenu);
                    break;
                } else if (menuChoice.equals("0")) {
                    break;
                } else {
                    throw new IllegalArgumentException("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31m숫자만 입력 해주세요!\u001B[0m");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }


    // 수량 선택 메서드
    private void quantityHandler(MenuItem selectMenu) {
        while (true) {
            try {
                int quantity = Integer.parseInt(Input("수량을 적어주세요 (0: 이전으로 돌아가기) : "));
                if(quantity != 0) {
                    addCartHandler(selectMenu, quantity);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31m숫자만 입력 해주세요!\u001B[0m");
            }
        }
    }

    // 장바구니에 메뉴 추가
    private void addCartHandler(MenuItem selectMenu, int quantity) {
        while (true) {
            try {
                CartItem cartItem = cart.getCartItem(selectMenu, quantity);
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("\u001B[36m1\u001B[0m. 확인     \u001B[36m2\u001B[0m. 취소");
                String addCart = Input("");
                if(addCart.equals("1")) {
                    cart.addCart(cartItem);
                    break;
                } else if (addCart.equals("2")) {
                    break;
                } else {
                    throw new IllegalArgumentException("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void cartMenu() {
        boolean flag = true;
        System.out.println();
        System.out.println("\033[38;5;214m[ CART MENU ]\033[0m");
        cart.cartList();
        cart.totalPrice();
        System.out.println("\u001B[36m1\u001B[0m. 삭제하기    \u001B[36m2\u001B[0m. 비우기    \u001B[36m3\u001B[0m. 메인으로 돌아가기");
        String select = Input("메뉴 번호를 입력하세요: ");
        switch (select) {
            case "1":
                System.out.println();
                for (int i = 0; i < cart.getCart().size(); i++) {
                    System.out.println(cart.getCart().get(i));
                }
                System.out.print("삭제할 메뉴 이름를 입력해주세요.");
                String removeMenu = input.next();
                CartItem item = cart.getCart().stream().filter(r -> r.getName().equals(removeMenu)).findFirst().orElseThrow();
                cart.getCart().remove(item);
                cartMenu();
                break;
            case "2":
                cart.getCart().clear();
                System.out.println("장바구니가 비었습니다.");
                flag = false;
                break;
            case "3":
                System.out.println();
                flag = false;
                break;
            default:
                System.out.println("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
        }

    }


    public void orderMenu() {
        System.out.println();
        System.out.println("\033[38;5;214m[ ORDERS ]\033[0m");
        cart.cartList();
        cart.totalPrice();
        System.out.println("\u001B[36m1\u001B[0m. 결제하기    \u001B[36m2\u001B[0m. 취소");
        String select = Input("메뉴 번호를 입력하세요: ");
        switch (select) {
            case "1" :
                Order order = new Order(cart.getCart());
                orderService.addOrder(order);
                cart.cartClear();
                System.out.println("주문이 완료되었습니다. " + "[ 주문번호: " + "\u001B[36m" + order.getId() + "\u001B[0m ]");
                break;
            case "2" :
                break;
            default:
                System.out.println("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
        }
    }




}
