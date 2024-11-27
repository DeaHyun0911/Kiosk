package Kiosk.utils;


import Kiosk.cart.CartItem;
import Kiosk.discount.Grade;
import Kiosk.menu.Category;
import Kiosk.menu.MenuItem;
import Kiosk.order.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrintService {

    public final MainPrinter main = new MainPrinter();
    public final ItemPrinter item = new ItemPrinter();
    public final CommonPrinter common = new CommonPrinter();
    public final CartPrinter cart = new CartPrinter();
    public final OrderPrinter order = new OrderPrinter();
    public final DiscountPrinter discount = new DiscountPrinter();

    // 콘솔 색상 관리
    public static String teal = "\033[36m";
    public static String orange = "\033[38;5;214m";

    public static String color(String text, String color) {
        String end = "\033[0m";
        return color + text + end;
    }

    // 공통 사용
    public class CommonPrinter {

        public void cancel() {
            System.out.println(color("0. ", teal) + "취소");
        }

        public void space() {
            System.out.println();
        }

        public void CheckNumber() {
            System.out.println("\u001B[31m메뉴번호를 확인해주세요!\u001B[0m");
        }
    }

    // 카테고리 메뉴 출력 관리
    public class MainPrinter {

        public void menus(Category[] categories, boolean hasCartItems, boolean hasOrderItems) {
            title();
            menu(categories);
            if (hasCartItems) {
                orderMenu();
                cartMenu();
            } else if (!hasOrderItems) {
                orderInfo();
            }
            exit();
        }

        public void title() {
            System.out.println();
            System.out.println(color("[ MOM'S TOUCH MENU ]", orange));
        }

        public void exit() {
            System.out.println(color("0. ", teal) + "EXIT");
        }

        public void cartMenu() {
            System.out.print(color("5. ", teal) + "CART    ");
        }

        public void orderMenu() {
            System.out.print(color("4. ", teal) + "ORDERS    ");
        }

        public void orderInfo() {
            System.out.print(color("4. ", teal) + "ORDER INFO    ");
        }

        public void menu(Category[] categories) {
            for (int i = 0; i < categories.length; i++) {
                System.out.print(color(String.valueOf((i + 1 + ". ")), teal) + categories[i] + "    ");
            }
        }
    }

    // 카테고리 선택에 따라 나오는 메뉴 출력 관리
    public class ItemPrinter {

        public void menus(Category category, List<MenuItem> itemMenu) {
            title(category);
            menu(itemMenu);
            common.cancel();
        }

        public void title(Category category) {
            System.out.println();
            System.out.println(color("[ " + category + " MENU ]", orange));
        }

        public void menu(List<MenuItem> itemMenu) {
            for (int i = 0; i < itemMenu.size(); i++) {
                System.out.println(color(String.valueOf((i + 1 + ". ")), teal) + itemMenu.get(i).getName() + " | " + itemMenu.get(i).getPrice() + "원 " + " | " + itemMenu.get(i).getDescription());
            }
        }


        public void select(MenuItem selectMenu) {
            System.out.println();
            System.out.println(color("선택한 메뉴: ", teal) + selectMenu.getName() + " | " + selectMenu.getPrice() + "원" + " | " + selectMenu.getDescription());
        }
    }

    // 장바구니 관련 출력
    public class CartPrinter {

        public void cartDisplayMenu(List<CartItem> cart, Long totalPrice) {
            title();
            cartList(cart);
            totalPrice(totalPrice);
            menu();
        }

        public void isAddCart(CartItem cartItem) {
            System.out.println();
            System.out.println("\u001B[36m선택한 메뉴\u001B[0m: " + cartItem.getName() + " | " + cartItem.getQuantity() + "개 | 합계: "+ cartItem.getPrice() + "원");
            System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
            System.out.println("\u001B[36m1\u001B[0m. 확인     \u001B[36m0\u001B[0m. 취소");
        }

        public void title() {
            System.out.println();
            System.out.println("\033[38;5;214m[ CART MENU ]\033[0m");
        }

        public static void cartList(List<CartItem> cart) {
            for (CartItem item : cart) {
                System.out.println("- " + item);
            }
        }

        public static void totalPrice(Long totalPrice) {
            System.out.println("합계: " + "\u001B[36m" + totalPrice + "\u001B[0m" + "원");
        }

        public void menu() {
            System.out.println("\u001B[36m1\u001B[0m. 삭제하기    \u001B[36m2\u001B[0m. 비우기    \u001B[36m0\u001B[0m. 메인으로 돌아가기");
        }

        public void addCart(CartItem item) {
            System.out.println("장바구니에 " + "\u001B[36m" + item.getName() + "\u001B[0m" + " 이(가) 추가되었습니다.");
        }

        public void cartIsEmpty() {
            System.out.println("장바구니가 비었습니다.");
        }

    }

    // 주문 관련 출력
    public class OrderPrinter {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH시mm분");

        public void orderDisplayMenu(List<CartItem> cart, Long totalPrice) {
            title();
            CartPrinter.cartList(cart);
            CartPrinter.totalPrice(totalPrice);
            menu();
        }

        public void orderInfo() {
            System.out.println("조회할 주문번호를 입력하세요. (0 입력 시 돌아가기)");
        }

        public void title() {
            System.out.println();
            System.out.println("\033[38;5;214m[ ORDERS ]\033[0m");
        }

        public void menu() {
            System.out.println("\u001B[36m1\u001B[0m. 결제하기    \u001B[36m2\u001B[0m. 취소");
        }

        public void receipt(Order order) {
            System.out.println("\033[1;34m+----------------------------------+\033[0m");
            System.out.println("\033[1;34m          주문번호 : " + order.getId() + "        \033[0m");
            System.out.println("\033[1;34m+----------------------------------+\033[0m");
//            System.out.println("\033[1;34m          " + order.getCreatedDate().format(dateFormat) + "        \033[0m");
//            System.out.println("\033[1;34m+----------------------------------+\033[0m");
            System.out.println(String.format("%-15s %5s %10s", "상품명", "수량", "가격"));
            order.getOrderList().forEach(item ->
                    System.out.printf("%-15s %5s %10s%n", item.getName(), item.getQuantity(), item.getPrice())
            );
            System.out.println("\033[1;34m+----------------------------------+\033[0m");
            System.out.printf("%-15s %15d원%n", "합계금액:", order.getPrice());
            System.out.printf("%-8s %5s %6d원%n", "할인금액:", order.getGrade().title + "혜택(" + order.getGrade().percent + "%)", -order.getDiscountPrice());
            System.out.printf("%-15s %15d원%n", "결제금액:", order.getTotalPrice());
            System.out.println("\033[1;34m+----------------------------------+\033[0m");
        }

        public void delayInfo(Order order, int delay) {
            LocalDateTime completeTime = order.getCreatedDate().plusMinutes(delay);
            System.out.println("\033[1;34m    주문일자: " + order.getCreatedDate().format(dateFormat) + "     \033[0m");
            System.out.println("\033[1;34m+----------------------------------+\033[0m");
            System.out.println("\033[1;34m예상대기시간은 " + delayToTime(delay) + ", \033[0m");
            System.out.println("\033[1;34m예상완료일시는 " + completeTime.format(dateFormat) + "입니다.\033[0m");
            System.out.println("\033[1;34m+----------------------------------+\033[0m");
        }

        public String delayToTime(int delay) {
            int days = delay / (24 * 60);
            int hours = (delay % (24 * 60)) / 60;
            int minutes = delay % 60;

            StringBuilder result = new StringBuilder();
            if (days > 0) {
                result.append(days).append("일 ");
            }
            if (hours > 0) {
                result.append(hours).append("시간 ");
            }
            if (minutes > 0) {
                result.append(minutes).append("분");
            }

            return result.toString();
        }

        public void easterEgg() {
            System.out.println("\033[33m신\033[0m께서 \033[33m전설의 버거\033[0m를 주문하여 버거가 즉시 완성됩니다.");
        }
    }

    // 할인 정책 관련 출력
    public class DiscountPrinter {

        public void discountDisplayMenu() {
            title();
            menu();
        }

        public void title() {
            System.out.println();
            System.out.println("\033[38;5;214m[ DISCOUNT ]\033[0m");
        }

        public void menu() {
            Grade[] grades = Grade.values();
            for (int i = 0; i < grades.length; i++) {
                System.out.println("\u001B[36m" + (i + 1) + "\u001B[0m. " + grades[i].title + " : " + grades[i].percent + "%");
            }
        }


    }


}
