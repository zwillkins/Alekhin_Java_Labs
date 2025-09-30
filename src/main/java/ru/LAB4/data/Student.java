package ru.LAB4.data;
import java.util.Map;
public class Student {
    String name;
    int age;
    Map<String, Integer> grades;
    public Student(String name, int age, Map<String, Integer> grades) { this.name = name; this.age = age; this.grades = grades; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public Map<String, Integer> getGrades() { return grades; }
}