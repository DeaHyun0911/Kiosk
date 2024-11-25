package Kiosk.cart;


import Kiosk.menu.MenuItem;

import java.util.List;

public interface CartService {

    CartItem getCartItem(MenuItem item, String quantity);

    void addCart(CartItem item);

    void removeCart(CartItem item);

    List<CartItem> getCart();

    void cartList();

    void cartMenu();

    void totalPrice();

    void cartClear();
}
