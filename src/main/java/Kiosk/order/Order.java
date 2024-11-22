package Kiosk.order;

import Kiosk.cart.CartItem;

import java.util.List;

public class Order {
    private Long id;
    private List<CartItem> orderList;
    private Long totalPrice;
    private Long discountPrice;

    public Order(Long id, List<CartItem> orderList, Long totalPrice, Long discountPrice) {
        this.id = id;
        this.orderList = orderList;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<CartItem> orderList) {
        this.orderList = orderList;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long calculatePrice() {
        return totalPrice - discountPrice;
    }
}
