package Kiosk.cart;

import Kiosk.menu.MenuItem;
import Kiosk.utils.PrintService;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private final List<CartItem> cart = new ArrayList<>();

    // 장바구니 아이템 생성
    @Override
    public CartItem setCartItem(MenuItem item, int quantity) {
        CartItem cartItem = new CartItem(item.getName(), quantity, item.getPrice(), item.getTime());
        return cartItem;
    }

    // 장바구니에 아이템 추가
    @Override
    public void addCart(CartItem item) {
        cart.add(item);
    }

    // 이름으로 아이템 삭제
    @Override
    public void removeCart(String removeItem) {
        CartItem item = getCart().stream().filter(r -> r.getName().equals(removeItem)).findFirst().orElseThrow();
        getCart().remove(item);
    }

    // 장바구니 리스트 반환
    @Override
    public List<CartItem> getCart() {
        return cart;
    }

    // 장바구니 합계 금액 반환
    @Override
    public Long totalPrice() {
        Long sum = 0L;
        if (!cart.isEmpty()) {
            for (CartItem item : cart) {
                sum += item.getPrice();
            }
        } else {
            System.out.println("장바구니가 비었습니다.");
        }

        return sum;
    }

    @Override
    public int totalDelay() {
        int sum = 0;
            for (CartItem item : cart) {
                sum += item.getDelay();
            }
        return sum;
    }


    // 장바구니 비우기
    @Override
    public void cartClear() {
        cart.clear();
        System.out.println("장바구니가 비었습니다.");
    }

    // 장바구니 비었는 지 확인
    @Override
    public boolean cartIsEmpty() {
        return cart.isEmpty();
    }


}

