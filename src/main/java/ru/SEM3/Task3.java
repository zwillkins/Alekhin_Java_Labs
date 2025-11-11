package ru.SEM3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Task3{

    public static void main(String[] args) throws InterruptedException {

        final ConcurrentMap<String, Integer> ingredients = new ConcurrentHashMap<>();
        ingredients.put("Паста", 10);
        ingredients.put("Стейк", 5);
        ingredients.put("Салат", 20);

        RestaurantOrder order1 = new RestaurantOrder("Паста", 1000, 500);
        RestaurantOrder order2 = new RestaurantOrder("Стейк", 2000, 1200);
        RestaurantOrder order3 = new RestaurantOrder("Стейк", 2000, 1200); 

        CompletableFuture<Void> future1 = processOrder(order1, ingredients);
        CompletableFuture<Void> future2 = processOrder(order2, ingredients);
        CompletableFuture<Void> future3 = processOrder(order3, ingredients);


        CompletableFuture.allOf(future1, future2, future3).join();
    }

    public static CompletableFuture<Void> processOrder(RestaurantOrder order, ConcurrentMap<String, Integer> ingredients) {
        System.out.println("\nПолучен новый заказ на: " + order.dishName);

        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Проверка ингредиентов для: " + order.dishName);
            Integer remaining = ingredients.compute(order.dishName, (key, value) -> {
                if (value == null || value == 0) return null; 
                return value - 1;
            });

            if (remaining != null) {
                System.out.println("Ингредиенты для '" + order.dishName + "' есть. Осталось на складе: " + remaining);
                return order;
            } else {
                throw new CompletionException(new Throwable("Нет ингредиентов для: " + order.dishName));
            }
        })
        .thenApplyAsync(o -> {
            System.out.println("Началось приготовление: " + o.dishName + " (займет " + o.cookingTimeMs + " мс)");
            try {
                Thread.sleep(o.cookingTimeMs); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Блюдо '" + o.dishName + "' готово.");
            return o;
        })
        .thenApply(o -> {
            double finalPrice = o.price;
            if (o.price > 1000) {
                finalPrice *= 0.90; 
                System.out.println("Для '" + o.dishName + "' применена скидка 10%. Итоговая цена: " + finalPrice);
            }
            return finalPrice;
        })
        .thenAccept(finalPrice -> {
            System.out.println("Уведомление: Заказ на " + order.dishName + " готов к выдаче! К оплате: " + finalPrice);
        })
        .exceptionally(ex -> {
            System.err.println("ОШИБКА с " + order.dishName + ": " + ex.getCause().getMessage());
            return null; 
        });
    }

    static class RestaurantOrder {
        String dishName;
        int cookingTimeMs;
        double price;

        public RestaurantOrder(String dishName, int cookingTimeMs, double price) {
            this.dishName = dishName;
            this.cookingTimeMs = cookingTimeMs;
            this.price = price;
        }
    }
}