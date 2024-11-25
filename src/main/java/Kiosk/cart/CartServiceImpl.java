package Kiosk.cart;

import Kiosk.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartServiceImpl implements CartService {
    private final List<CartItem> cart = new ArrayList<>();

    public CartServiceImpl() {
        System.out.println("CartServiceImpl 생성");
    }

    @Override
    public CartItem getCartItem(MenuItem item, String quantity) {
        CartItem cartItem = new CartItem(item.getName(), Integer.parseInt(quantity), item.getPrice());
        System.out.println("");
        System.out.println("\u001B[36m선택한 메뉴\u001B[0m: " + cartItem.getName() + " | " + cartItem.getQuantity() + "개 | 합계: "+ cartItem.getQuantity()*cartItem.getPrice() + "원");
        return cartItem;
    }

    @Override
    public void addCart(CartItem item) {
        System.out.println("장바구니에 " + "\u001B[36m" + item.getName() + "\u001B[0m" + " 이(가) 추가되었습니다.");
        cart.add(item);
    }


    @Override
    public void removeCart(CartItem item) {
        cart.remove(item);
    }

    @Override
    public List<CartItem> getCart() {
        return cart;
    }

    @Override
    public void cartList() {
        System.out.println("장바구니 목록");
        for (CartItem item : cart) {
            System.out.println("- " + item);
        }
    }

    @Override
    public void cartMenu() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        System.out.println();
        System.out.println("\033[38;5;214m[ CART MENU ]\033[0m");
        cartList();
        totalPrice();
        System.out.println("\u001B[36m1\u001B[0m. 삭제하기    \u001B[36m2\u001B[0m. 비우기    \u001B[36m3\u001B[0m. 메인으로 돌아가기");
        String select = input.next();
        switch (select) {
            case "1":
                System.out.println();
                for (int i = 0; i < cart.size(); i++) {
                    System.out.println(cart.get(i));
                }
                System.out.print("삭제할 메뉴 이름를 입력해주세요.");
                String removeMenu = input.next();
                CartItem item = cart.stream().filter(r -> r.getName().equals(removeMenu)).findFirst().orElseThrow();
                cart.remove(item);
                cartMenu();
                break;
            case "2":
                cart.clear();
                System.out.println("장바구니가 비었습니다.");
                flag = false;
                break;
            case "3":
                System.out.println();
                flag = false;
                break;
            default:
                System.out.println("잘못 입력했습니다.");
        }

    }

    @Override
    public void totalPrice() {
        int sum = 0;
        if (!cart.isEmpty()) {
            for (CartItem item : cart) {
                sum += item.getPrice();
            }
            System.out.println("합계: " + "\u001B[36m" + sum + "\u001B[0m" + "원");
        } else {
            System.out.println("장바구니가 비었습니다.");
        }
    }

    @Override
    public void cartClear() {
        cart.clear();
    }


}

