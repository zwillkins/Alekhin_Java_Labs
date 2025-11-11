package ru.bmstu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.bmstu.domain.Student;


public class StudentManager {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Добавлен студент: " + student.getFirstName());
    }

    public Student findByName(String name) {
        return students.stream()
                       .filter(s -> s.getFirstName().equalsIgnoreCase(name))
                       .findFirst()
                       .orElse(null);
    }

    public List<Student> findByAverageGrade(double minAverage) {
        return students.stream()
                       .filter(s -> s.getAverageGrade() >= minAverage)
                       .collect(Collectors.toList());
    }

    public void removeStudent(String name) {
        boolean removed = students.removeIf(s -> s.getFirstName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println("Студент " + name + " удален.");
        } else {
            System.out.println("Студент " + name + " не найден.");
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students); 
    }
}
