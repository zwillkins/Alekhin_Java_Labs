package ru.bmstu.service;

import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;
import ru.bmstu.exception.InvalidPhoneNumberException;

import java.util.List;

public class PersonDemo {


    public void demonstrateExceptions() {
        System.out.println("\n=== Демонстрация обработки исключений ===");
        Person person = new Person("Иван", "Иванов", 30);
        
        try {
            person.setPhone("+7 (999) 123-45-67");
            System.out.println("  Успешно установлен корректный номер: " + person.getPhone());
        } catch (InvalidPhoneNumberException e) {
            System.out.println("  Произошла ошибка: " + e.getMessage());
        }

        System.out.println("\n  Попытка установить номер с буквами...");
        try {
            person.setPhone("не номер телефона");
        } catch (InvalidPhoneNumberException e) {
            System.out.println("    Успешно поймано исключение: " + e.getMessage());
        }

        System.out.println("  Попытка установить слишком короткий номер...");
        try {
            person.setPhone("123");
        } catch (InvalidPhoneNumberException e) {
            System.out.println("    Успешно поймано исключение: " + e.getMessage());
        }
    }


    public void demonstrateStudentManager() {
        System.out.println("\n=== Демонстрация работы StudentManager ===");
        StudentManager manager = new StudentManager();

        Student student1 = new Student("Петр", "Петров", 20, "ИУ7-41Б", 2);
        student1.addSubject("Физика", 5);
        student1.addSubject("Математика", 4);
        student1.addSubject("Алгоритмы", 5);

        Student student2 = new Student("Анна", "Сидорова", 19, "ИУ7-42Б", 2);
        student2.addSubject("Программирование", 5);
        student2.addSubject("Базы данных", 4);

        manager.addStudent(student1);
        manager.addStudent(student2);
        System.out.println();

        StudentPrinter.printStudentInfo(student1);
        StudentPrinter.printStudentInfo(student2);

        System.out.println("\nПоиск студента по имени 'Петр'...");
        Student foundStudent = manager.findByName("Петр");
        if (foundStudent != null) {
            System.out.println("  Найден: " + foundStudent.getFirstName() + ", группа " + foundStudent.getGroup());
        }

        System.out.println("\nПоиск студентов со средним баллом выше 4.5...");
        List<Student> highAchievers = manager.findByAverageGrade(4.5);
        if (highAchievers.isEmpty()) {
            System.out.println("  Таких студентов нет.");
        } else {
            for (Student s : highAchievers) {
                System.out.println("  - " + s.getFirstName() + " (ср. балл " + String.format("%.2f", s.getAverageGrade()) + ")");
            }
        }
        
        System.out.println("\nУдаление студента 'Анна'...");
        manager.removeStudent("Анна");
        
        System.out.println("\nТекущий список студентов:");
        manager.getAllStudents().forEach(s -> System.out.println("  - " + s.getFirstName()));
    }
}