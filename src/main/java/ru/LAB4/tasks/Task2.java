package ru.LAB4.tasks;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import ru.LAB4.data.Employee;

public class Task2 {
    public static void solve() {
        System.out.println("\n--- Задание 2: Сотрудники ---");
        List<Employee> employees = createEmployeeData();

        System.out.println("Топ-3 самых молодых IT-сотрудника с зарплатой > 50.000:");
        employees.stream()
            .filter(e -> e.getSalary() > 50000)
            .filter(e -> "IT".equals(e.getDepartment()))
            .sorted(Comparator.comparingInt(Employee::getAge))
            .limit(3)
            .forEach(e -> System.out.println("  - " + e.getName() + ", возраст: " + e.getAge()));
    }

    private static List<Employee> createEmployeeData() {
        return Arrays.asList(
            new Employee("Паша", 30, "HR", 70000),
            new Employee("Маша", 22, "IT", 80000),
            new Employee("Гоша", 28, "IT", 40000),
            new Employee("Миша", 21, "IT", 90000),
            new Employee("Гриша", 24, "IT", 100000)
        );
    }
}