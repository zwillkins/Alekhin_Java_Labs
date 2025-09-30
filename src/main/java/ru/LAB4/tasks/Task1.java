package ru.LAB4.tasks;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.LAB4.data.Student;

public class Task1 {
    public static void solve() {
        System.out.println("\n--- Задание 1: Студенты ---");
        List<Student> students = createStudentData();

        List<String> filteredStudentNames = students.stream()
            .filter(student -> student.getAge() > 20)
            .filter(student -> student.getGrades().values().stream().anyMatch(grade -> grade > 80))
            .sorted(Comparator.comparing(Student::getName))
            .map(Student::getName)
            .collect(Collectors.toList());

        System.out.println("Студенты старше 20 с оценкой > 80:");
        filteredStudentNames.forEach(name -> System.out.println("  - " + name));
    }
    
    private static List<Student> createStudentData() {
        return Arrays.asList(
            new Student("Альберт", 21, Map.of("JAVA", 85, "ООП", 78)),
            new Student("Вова", 19, Map.of("ОЭ", 95, "ASM", 88)),
            new Student("Рома", 22, Map.of("КГ", 70, "OS", 75)),
            new Student("Семён", 23, Map.of("ОП", 92, "Алгоритмы", 88))
        );
    }
}