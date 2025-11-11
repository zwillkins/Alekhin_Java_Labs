package ru.SEM3;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Task2{

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final int TOTAL_ORDERS = 10;
        final int CONSUMER_THREADS = 3;

        final Queue<String> orderQueue = new ArrayBlockingQueue<>(TOTAL_ORDERS);
        final ReentrantLock lock = new ReentrantLock();
        final AtomicInteger processedOrdersCount = new AtomicInteger(0);
        final ExecutorService executor = Executors.newFixedThreadPool(CONSUMER_THREADS);
        final Random random = new Random();

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < TOTAL_ORDERS; i++) {
                lock.lock(); 
                try {
                    String order = "Заказ #" + (i+1);
                    orderQueue.add(order);
                    System.out.println("Производитель:" + order + "'. В очереди: " + orderQueue.size());
                } finally {
                    lock.unlock(); 
                }
                try {
                    Thread.sleep(random.nextInt(100) + 50); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        List<Future<String>> consumerFutures = new ArrayList<>();
        for (int i = 0; i < CONSUMER_THREADS; i++) {
            final int consumerId = i+1;
            Callable<String> consumerTask = () -> {
                while (processedOrdersCount.get() < TOTAL_ORDERS) {
                    String order = null;
                    lock.lock();
                    try {
                        if (!orderQueue.isEmpty()) {
                            order = orderQueue.poll();
                        }
                    } finally {
                        lock.unlock();
                    }

                    if (order != null) {
                        System.out.println("Потребитель #" + consumerId + ": Обрабатывает '" + order + "'");
                        Thread.sleep(random.nextInt(500) + 100);
                        processedOrdersCount.incrementAndGet(); 
                        System.out.println("Потребитель #" + consumerId + ": Обработал '" + order + "'. Всего обработано: " + processedOrdersCount.get());
                    }
                }
                return "Потребитель #" + consumerId + " завершил работу.";
            };
            consumerFutures.add(executor.submit(consumerTask));
        }

        producerThread.start();
        
        for (Future<String> future : consumerFutures) {
            System.out.println(future.get()); 
        }
        
        executor.shutdown();
        System.out.println("\nИтоговое количество обработанных заказов: " + processedOrdersCount.get());
    }
}