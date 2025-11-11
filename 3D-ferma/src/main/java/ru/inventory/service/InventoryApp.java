package ru.inventory.service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import ru.inventory.domain.FilamentSpool;
import ru.inventory.domain.PETGSpool;
import ru.inventory.domain.PLASpool;
import ru.inventory.domain.Warehouse;

public class InventoryApp {
    private Warehouse warehouse; 
    private final FileManager fileManager = new FileManager();
    private final Scanner scanner = new Scanner(System.in);
    private final String JSON_PATH = "/home/sratik/Alekhin_Java_Labs/3D-ferma/warehouse.json";
    private final String TEXT_PATH = "/home/sratik/Alekhin_Java_Labs/3D-ferma/warehouse.txt";

    public InventoryApp() {
        try {
            this.warehouse = fileManager.loadFromJson(JSON_PATH);
            System.out.println("Данные загружены из " + JSON_PATH);
        } catch (IOException e) {
            System.out.println("Файл " + JSON_PATH + " не найден."
            );
            this.warehouse = new Warehouse();
        }
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addNewSpool();
                    case 2 -> recordUsage();
                    case 3 -> displayInventory();
                    case 4 -> saveDataMenu(); 
                    case 5 -> loadDataMenu(); 
                    case 0 -> running = false; 
                    default -> System.out.println("Неверный выбор.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
        System.out.println("Программа завершена.");
    }
    
    private void printMenu() {
        System.out.println("1. Добавить новую катушку");
        System.out.println("2. Списать пластик");
        System.out.println("3. Показать инвентарь");
        System.out.println("4. Сохранить");
        System.out.println("5. Загрузить"); 
        System.out.println("0. Выйти");                     
        System.out.print("Ваш выбор: ");
    }

    private void saveDataMenu() {
        System.out.println("\nВ каком формате сохранить?");
        System.out.println("1. JSON");
        System.out.println("2. Текстовый файл");
        System.out.println("0. Отмена");
        System.out.print("Ваш выбор: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                fileManager.saveToJson(warehouse, JSON_PATH);
                System.out.println("Данные успешно сохранены в " + JSON_PATH);
            } else if (choice == 2) {
                fileManager.saveToText(warehouse, TEXT_PATH);
                System.out.println("Данные успешно сохранены в " + TEXT_PATH);
            }
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите число.");
        }
    }

   private void loadDataMenu() {
    System.out.println("Из какого формата загрузить данные?");
    System.out.println("1. JSON");
    System.out.println("2. Текстовый файл");
    System.out.println("0. Отмена");
    System.out.print("Ваш выбор: ");
    try {
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice == 1) {
            this.warehouse = fileManager.loadFromJson(JSON_PATH);
            System.out.println("Данные успешно загружены из " + JSON_PATH);
        } else if (choice == 2) {
            this.warehouse = fileManager.loadFromText(TEXT_PATH);
            System.out.println("Данные успешно загружены из " + TEXT_PATH);
        }
        
    } catch (IOException e) {
        System.out.println("Ошибка загрузки: " + e.getMessage());
        this.warehouse = new Warehouse(); 
    } catch (NumberFormatException e) {
        System.out.println("Ошибка: введите число.");
    }
}
    
    private void addNewSpool() {
        try {
            System.out.print("Введите ID катушки (число): ");
            long id = Long.parseLong(scanner.nextLine());
            System.out.print("Введите цвет: ");
            String color = scanner.nextLine();
            System.out.print("Введите вес (например, 1000г): ");
            int weight = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите тип пластика (PLA или PETG): ");
            String type = scanner.nextLine().toUpperCase();
    
            FilamentSpool newSpool;
            if ("PLA".equals(type)) {
                newSpool = new PLASpool(id, color, weight, 210);
            } else if ("PETG".equals(type)) {
                newSpool = new PETGSpool(id, color, weight, true);
            } else {
                System.out.println("Неизвестный тип пластика.");
                return;
            }
            warehouse.addSpool(newSpool);
            System.out.println("Катушка " + type + " ID:" + id + " добавлена на склад.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: ID и вес должны быть числами.");
        }
    }

    private void recordUsage() {
        try {
            System.out.print("Введите ID катушки для списания: ");
            long id = Long.parseLong(scanner.nextLine());

            warehouse.findSpoolById(id).ifPresentOrElse(
                spool -> {
                    try {
                        System.out.print("Текущий вес: " + spool.getWeight() + "г. Сколько грамм списать? ");
                        int amountToUse = Integer.parseInt(scanner.nextLine());
                        if (amountToUse > spool.getWeight() || amountToUse < 0) {
                            System.out.println("Ошибка: некорректное количество для списания.");
                        } else {
                            spool.setWeight(spool.getWeight() - amountToUse);
                            System.out.println("Списание прошло успешно. Новый вес: " + spool.getWeight() + "г.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода: количество для списания должно быть числом.");
                    }
                },
                () -> System.out.println("Катушка с ID " + id + " не найдена.")
            );
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: ID должен быть числом.");
        }
    }

    private void displayInventory() {
        System.out.println("\n--- Текущее состояние склада ---");
        List<FilamentSpool> spools = warehouse.getSpools();
        if (spools.isEmpty()) {
            System.out.println("Склад пуст.");
        } else {
            System.out.println("ID | Тип  | Цвет        | Остаток (г)");
            System.out.println("---|------|-------------|-----------");
            for (FilamentSpool spool : spools) {
                System.out.printf("%-2d | %-4s | %-11s | %d\n",
                    spool.getId(), spool.getMaterialType(), spool.getColor(), spool.getWeight());
            }
        }
        System.out.println("---------------------------------");
    }
}