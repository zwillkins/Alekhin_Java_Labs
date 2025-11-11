package ru.LAB5;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Task3 {

    public static void main(String[] args) {

        OrderQueue orderQueue = new OrderQueue(3); 

        Thread waiterThread = new Thread(new Waiter(orderQueue));
        waiterThread.start();

        Thread chefThread1 = new Thread(new Chef("Повар-1", orderQueue));
        Thread chefThread2 = new Thread(new Chef("Повар-2", orderQueue));
        chefThread1.start();
        chefThread2.start();
    }

//-----------------------------------------------------------------------
    static class Dish {
        private final String name;
        public Dish(String name) { this.name = name; }
        @Override public String toString() { return "Блюдо '" + name + "'"; }
    }
//-----------------------------------------------------------------------
    static class Order {
        private static int counter = 0;
        private final int id = ++counter;
        private final Dish dish;
        public Order(Dish dish) { this.dish = dish; }
        public int getId() { return id; }
        public Dish getDish() { return dish; }
    }

//-----------------------------------------------------------------------
    static class OrderQueue {
        private final Queue<Order> queue = new LinkedList<>();
        private final int capacity;

        public OrderQueue(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void addOrder(Order order) throws InterruptedException {
            while (queue.size() == capacity) {
                System.out.println("Кухня переполнена!");
                wait(); 
            }
            queue.add(order);
            System.out.println("Официант передал на кухню заказ #" + order.getId() + " (" + order.getDish() + ")");
            notifyAll(); 
        }

        public synchronized Order takeOrder() throws InterruptedException {
            while (queue.isEmpty()) {
                System.out.println("Заказов нет.");
                wait();
            }
            Order order = queue.poll();
            System.out.println("Повар взял в работу заказ #" + order.getId());
            notifyAll(); 
            return order;
        }
    }

//-----------------------------------------------------------------------
    static class Waiter implements Runnable {
        private final OrderQueue orderQueue;
        public Waiter(OrderQueue orderQueue) { this.orderQueue = orderQueue; }
        
        @Override
        public void run() {
            Random random = new Random();
            try {
                for (int i = 0; i < 10; i++) {
                    Dish dish = new Dish("Блюдо-" + (i + 1));
                    orderQueue.addOrder(new Order(dish));
                    Thread.sleep(random.nextInt(500)); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

//-----------------------------------------------------------------------
    static class Chef implements Runnable {
        private final String name;
        private final OrderQueue orderQueue;
        public Chef(String name, OrderQueue orderQueue) {
            this.name = name;
            this.orderQueue = orderQueue;
        }
        
        @Override
        public void run() {
            Random random = new Random();
            try {
                while (true) {
                    Order order = orderQueue.takeOrder();
                    System.out.println(name + " готовит заказ #" + order.getId() + "...");
                    Thread.sleep(random.nextInt(1000) + 500); 
                    System.out.println(name + " закончил готовить заказ #" + order.getId() + ". Возвращает официанту.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}