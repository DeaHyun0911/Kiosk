package Kiosk.order;

import Kiosk.cart.CartItem;
import Kiosk.discount.Grade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int currentId = 0;

    private Long id;
    private Grade grade;
    private List<CartItem> orderList;
    private Long price;
    private Long discountPrice;
    private Long totalPrice;
    private LocalDateTime createdDate;

    public Order(List<CartItem> orderList) {
        this.id = (long) generateId();
        this.grade = Grade.PUBLIC;
        this.orderList = new ArrayList<>(orderList);
        this.price = calculatePrice(orderList);
        this.discountPrice = 0L;
        this.totalPrice = price - discountPrice;
        this.createdDate = LocalDateTime.now();
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
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

    public void setTotalPrice() {
        this.totalPrice = price - discountPrice;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Grade grade) {
        this.discountPrice = getPrice() * grade.percent / 100;
        setTotalPrice();
        setGrade(grade);
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
                ", grade=" + grade +
                ", orderList=" + orderList +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", totalPrice=" + totalPrice +
                ", createdDate=" + createdDate +
                '}';
    }
}
