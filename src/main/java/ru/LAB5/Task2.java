package ru.LAB5;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task2 {

    public static void main(String[] args) throws InterruptedException {

        final int NUM_ACCOUNTS = 5;
        final int NUM_CLIENTS = 5;
        final List<BankAccount> accounts = new ArrayList<>();
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts.add(new BankAccount(i, 1000)); 
        }

        List<Thread> clientThreads = new ArrayList<>();
        for (int i = 0; i < NUM_CLIENTS; i++) {
            Client client = new Client("Client-" + (i + 1), accounts);
            Thread thread = new Thread(client);
            clientThreads.add(thread);
            thread.start();
        }

        Thread.sleep(3000);

        System.out.println("\nВремя вышло! Прерываем все транзакции.\n");
        for (Thread thread : clientThreads) {
            thread.interrupt();
        }

        for (Thread thread : clientThreads) {
            thread.join();
        }
        
        System.out.println("\n-------------------------");
        long totalMoney = 0;
        for (BankAccount account : accounts) {
            System.out.println(account);
            totalMoney += account.getBalance();
        }
        System.out.println("Общая сумма денег в банке: " + totalMoney);
    }

// -----------------------------------------------------------------------------------
    static class BankAccount {
        private final int id;
        private long balance;

        public BankAccount(int id, long initialBalance) {
            this.id = id;
            this.balance = initialBalance;
        }

        public synchronized void withdraw(long amount) {
            balance -= amount;
        }

        public synchronized void deposit(long amount) {
            balance += amount;
        }

        public long getBalance() {
            return balance;
        }
        
        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Счет #" + id + ", баланс: " + balance;
        }
    }

// -----------------------------------------------------------------------------------
   static class Client implements Runnable {
        private final String name;
        private final List<BankAccount> accounts;
        private final Random random = new Random();
        private int transactionCount = 0;

        public Client(String name, List<BankAccount> accounts) {
            this.name = name;
            this.accounts = accounts;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                BankAccount from = accounts.get(random.nextInt(accounts.size()));
                BankAccount to = accounts.get(random.nextInt(accounts.size()));
                
                if (from.getId() == to.getId()) continue;

                long amount = random.nextInt(100) + 1;

                BankAccount lock1 = from.getId() < to.getId() ? from : to;
                BankAccount lock2 = from.getId() < to.getId() ? to : from;

                synchronized (lock1) {
                    synchronized (lock2) {
                        if (from.getBalance() >= amount) {
                            from.withdraw(amount);
                            to.deposit(amount);
                            transactionCount++;
                        }
                    }
                }

                if (transactionCount > 1000) {
                    System.out.println(name + " выполнил лимит в 1000 транзакций.");
                    Thread.currentThread().interrupt();
                }

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); 
                    break;
                }
            }
            System.out.println(name + " завершил работу. Всего транзакций: " + transactionCount);
        }
    }
}