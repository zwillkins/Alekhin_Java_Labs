package ru.LAB4.data;
import java.time.LocalDate;
import java.util.List;
public class Order {
    int orderId;
    List<Item> items;
    LocalDate orderDate;
    public Order(int orderId, List<Item> items, LocalDate orderDate) { this.orderId = orderId; this.items = items; this.orderDate = orderDate; }
    public int getOrderId() { return orderId; }
    public List<Item> getItems() { return items; }
    public LocalDate getOrderDate() { return orderDate; }
}