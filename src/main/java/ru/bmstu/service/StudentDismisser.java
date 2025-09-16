package ru.bmstu.service;

import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;

public class StudentDismisser implements Dismissable {
    @Override
    public void dismiss(Person p) {
        System.out.println("--- Приказ об отчислении ---");
        
        if (p instanceof Student)
        {
            Student s = (Student) p;
            System.out.println("Студент: " + s.getFirstName() + " " + s.getSecondName());
            System.out.println("Курс: " + s.getCourse());
            System.out.println("Группа: " + s.getGroup());
            System.out.println("Статус: ОТЧИСЛЕН");
        }
        
        else
        {
            System.out.println("Ошибка: Данный человек не является студентом.");
            Dismissable.super.dismiss(p);
        }
        System.out.println("-----------------------------");
    }
}