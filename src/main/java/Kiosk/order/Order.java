package Kiosk.order;

import Kiosk.cart.CartItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int currentId = 0;

    private Long id;
    private List<CartItem> orderList;
    private Long price;
    private Long discountPrice;
    private Long totalPrice;
    private LocalDateTime createdDate;

    public Order(List<CartItem> orderList) {
        this.id = (long) generateId();
        this.orderList = new ArrayList<>(orderList);
        this.price = calculatePrice(orderList);
        this.discountPrice = 0L;
        this.totalPrice = price - discountPrice;
        this.createdDate = LocalDateTime.now();
    }


    public static int generateId() {
        return ++currentId;
    }

    public Long getId() {
        return id;
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

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        Order.currentId = currentId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    private Long calculatePrice(List<CartItem> orderList) {
        return orderList.stream()
                .mapToLong(item -> item.getPrice()) // 가격 * 수량
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderList=" + orderList +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
