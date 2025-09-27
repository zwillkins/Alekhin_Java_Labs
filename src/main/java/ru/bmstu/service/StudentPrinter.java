package ru.bmstu.service;

import ru.bmstu.domain.Student;
import ru.bmstu.domain.Subject;

public final class StudentPrinter {
    private StudentPrinter() {}


    public static void printStudentInfo(Student student) {
        System.out.println("--- Информация о студенте ---");
        System.out.println("ФИО: " + student.getFirstName() + " " + student.getSecondName());
        System.out.println("Возраст: " + student.getAge());
        System.out.println("Группа: " + student.getGroup());
        System.out.println("Курс: " + student.getCourse());
        System.out.println("Средний балл: " + String.format("%.2f", student.getAverageGrade()));

        System.out.println("Дисциплины (отсортированы в обратном алфавитном порядке):");
        if (student.getSubjects().isEmpty()) {
            System.out.println("  Список пуст.");
        } else {
            for (Subject subject : student.getSubjects()) {
                System.out.println("  - " + subject); 
            }
        }
        System.out.println("-----------------------------");
    }
}