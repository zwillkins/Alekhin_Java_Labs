package ru.LAB4.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ru.LAB4.data.Customer;
import ru.LAB4.data.Purchase;

public class Task5 {
    public static void solve() {
        System.out.println("\n--- Задание 5: Клиенты ---");
        List<Customer> customers = createCustomerData();

        List<String> customerEmails = customers.stream()
            .filter(c -> c.getPurchases().stream()
                .filter(p -> p.getDate().isAfter(LocalDate.now().minusMonths(1)))
                .mapToDouble(Purchase::getAmount)
                .sum() > 1000)
            
            .peek(customer -> customer.getPurchases().stream()
                .filter(p -> "элекроника".equals(p.getCategory()))
                .filter(p -> p.getDate().isAfter(LocalDate.now().minusWeeks(2)))
                .forEach(purchase -> {
                    purchase.setAmount(purchase.getAmount() * 1.10);
                }))
            
            .sorted(Comparator.comparingInt((Customer c) -> c.getPurchases().size()).reversed())
            
            .map(Customer::getEmail)
            .collect(Collectors.toList());

        System.out.println("\nПочта клиентов с покупками > 1000 за месяц (отсортированы по числу покупок):");
        customerEmails.forEach(email -> System.out.println("  - " + email));
    }

    private static List<Customer> createCustomerData() {
        Customer john = new Customer("pavel.levl@example.com", new ArrayList<>(Arrays.asList(
            new Purchase("продукты", 150.0, LocalDate.now().minusDays(40)),
            new Purchase("електроника", 1200.0, LocalDate.now().minusDays(5))
        )));
        Customer jane = new Customer("semen.smith@example.com", new ArrayList<>(Arrays.asList(
            new Purchase("одежда", 80.0, LocalDate.now().minusDays(20))
        )));
        Customer mike = new Customer("miha.tapok@example.com", new ArrayList<>(Arrays.asList(
            new Purchase("книги", 50.0, LocalDate.now().minusDays(10)),
            new Purchase("книги", 60.0, LocalDate.now().minusDays(15)),
            new Purchase("продукты", 1100.0, LocalDate.now().minusDays(3))
        )));
        return Arrays.asList(john, jane, mike);
    }
}