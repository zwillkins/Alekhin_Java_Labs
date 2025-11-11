package ru.LAB5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. массив");
            System.out.println("2. переводы");
            System.out.println("3. ресторан ");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите число.");
                continue;
            }

            switch (choice) {
                case 1:
                    Task1.main(null);
                    break;
                case 2:
                    Task2.main(null);
                    break;
                case 3:
                    Task3.main(null);
                    break;
                case 0:
                    scanner.close();
                    return; 
                default:
                    System.out.println("Неверный выбор.");
            }
            scanner.nextLine();
        }
    }
}