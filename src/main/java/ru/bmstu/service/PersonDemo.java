package ru.bmstu.service;

import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;

public class PersonDemo {
    public void demonstratePerson() {
        System.out.println("=== С Person ===");
        
        Person person1 = new Person("Иван", "Пятросянов", 30, "+7-555-510-55-10");
        Person person2 = new Person("Анна", "Пятёрочкина", 25);

        PersonPrinter.printFI(person1);
        PersonPrinter.printInfo(person1);

        PersonPrinter.printInfo(person2);
        person2.setPhone("+7-111-150-11-50");
        System.out.println("\nПосле добавления телефона для Анны:");
        PersonPrinter.printInfo(person2);
    }

    public void demo() {
        Student student = new Student("Семён", "Шилов", 20, "ИУК5-51Б", 2);

        PersonPrinter.printInfo(student);

        Dismissable dismisser = new StudentDismisser();
        
        dismisser.dismiss(student);
    }
}