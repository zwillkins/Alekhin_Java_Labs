package ru.LAB4.tasks;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import ru.LAB4.data.Item;
import ru.LAB4.data.Order;

public class Task3 {
    public static void solve() {
        System.out.println("\n--- Задание 3: Заказы ---");
        List<Order> orders = createOrderData();

        double totalCost = orders.stream()
            .filter(order -> order.getOrderDate().isAfter(LocalDate.now().minusMonths(1)))
            
            .filter(order -> order.getItems().stream().anyMatch(item -> "одежда".equals(item.getCategory())))

            .mapToDouble(order -> order.getItems().stream().mapToDouble(Item::getPrice).sum())

            .sum();

        System.out.printf("Общая стоимость заказов с одеждой за последний месяц: %.2f\n", totalCost);
    }

    private static List<Order> createOrderData() {
        return Arrays.asList(
            new Order(1, List.of(new Item("одежда", 50.0), new Item("туфли", 120.0)), LocalDate.now().minusDays(10)),
            new Order(2, List.of(new Item("книги", 25.0)), LocalDate.now().minusMonths(2)),
            new Order(3, List.of(new Item("электроника", 500.0)), LocalDate.now().minusDays(5)),
            new Order(4, List.of(new Item("одежда", 75.50)), LocalDate.now().minusDays(1))
        );
    }
}