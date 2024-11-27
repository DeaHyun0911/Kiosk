package Kiosk.cart;


import Kiosk.menu.MenuItem;

import java.util.List;

public interface CartService {

    CartItem setCartItem(MenuItem item, int quantity);

    void addCart(CartItem item);

    void removeCart(String removeItem);

    List<CartItem> getCart();

    Long totalPrice();

    void cartClear();

    boolean cartIsEmpty();
}
