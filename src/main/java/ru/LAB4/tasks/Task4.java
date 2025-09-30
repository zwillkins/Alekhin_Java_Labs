package ru.LAB4.tasks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ru.LAB4.data.Product; 

public class Task4 {
    public static void solve() {
        System.out.println("\n--- Задание 4: Товары ---");
        List<Product> products = createProductData();

        List<String> availableProducts = products.stream()
            .filter(p -> p.getPrice() > 50)
            .filter(p -> p.getQuantity() > 0)
            .sorted(Comparator.comparingInt(Product::getQuantity))
            .map(Product::getName)
            .collect(Collectors.toList());

        System.out.println("Товары в наличии дороже 50 (отсортированы по количеству):");
        availableProducts.forEach(name -> System.out.println("  - " + name));
    }

    private static List<Product> createProductData() {
        return Arrays.asList(
            new Product("Ноуты", 1200.0, 10),
            new Product("Мышки", 25.0, 30),
            new Product("Клавиатуры", 75.0, 0),
            new Product("Мониторы", 300.0, 5)
        );
    }
}