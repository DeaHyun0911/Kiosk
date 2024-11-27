package Kiosk.cart;

import java.util.ArrayList;
import java.util.List;

public class CartItem {
    private String name;
    private int quantity;
    private int price;
    private int delay;

    public CartItem(String name, int quantity, int price, int time) {
        this.name = name;
        this.quantity = quantity;
        this.price = price * quantity;
        this.delay = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " | " + quantity + "개 | " + price + "원";
    }

    public String toString2()  {
        return name + " | " + quantity + "개 | " + price + "원" + delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
