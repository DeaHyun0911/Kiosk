package Kiosk.cart;

import Kiosk.menu.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {
    private final List<CartItem> cart = new ArrayList<>();

    @Override
    public CartItem getCartItem(MenuItem item, int quantity) {
        CartItem cartItem = new CartItem(item.getName(), quantity, item.getPrice());
        System.out.println("");
        System.out.println("\u001B[36m선택한 메뉴\u001B[0m: " + cartItem.getName() + " | " + cartItem.getQuantity() + "개 | 합계: "+ cartItem.getPrice() + "원");
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
    public Long totalPrice() {
        Long sum = 0L;
        if (!cart.isEmpty()) {
            for (CartItem item : cart) {
                sum += item.getPrice();
            }
            System.out.println("합계: " + "\u001B[36m" + sum + "\u001B[0m" + "원");
        } else {
            System.out.println("장바구니가 비었습니다.");
        }

        return sum;
    }

    @Override
    public void cartClear() {
        cart.clear();
    }


}

