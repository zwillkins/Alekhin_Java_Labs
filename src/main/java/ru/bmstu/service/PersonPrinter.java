package ru.bmstu.service;
import ru.bmstu.domain.Person;

public final class PersonPrinter {
    private PersonPrinter() {
    }

    public static void printFI(Person p) {
        System.out.println("Имя: " + p.getFirstName() + ", Фамилия: " + p.getSecondName());
    }

    public static void printInfo(Person p) {
        System.out.println("--- Полная информация ---");
        System.out.println("Имя: " + p.getFirstName());
        System.out.println("Фамилия: " + p.getSecondName());
        System.out.println("Возраст: " + p.getAge());
        String phoneInfo = (p.getPhone() != null) ? p.getPhone() : "-";
        System.out.println("Телефон: " + phoneInfo);
        System.out.println("-------------------------");
    }
}