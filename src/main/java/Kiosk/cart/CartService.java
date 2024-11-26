package Kiosk.cart;


import Kiosk.menu.MenuItem;

import java.util.List;

public interface CartService {

    CartItem getCartItem(MenuItem item, int quantity);

    void addCart(CartItem item);

    void removeCart(CartItem item);

    List<CartItem> getCart();

    void cartList();

    Long totalPrice();

    void cartClear();

    boolean cartIsEmpty();
}
