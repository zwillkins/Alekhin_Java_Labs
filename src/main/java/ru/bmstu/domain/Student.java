package ru.bmstu.domain;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Student extends Person {
    private String group;
    private int course;
    
    private final SortedSet<Subject> subjects = new TreeSet<>(Comparator.comparing(Subject::getName).reversed());

    public Student(String firstName, String secondName, int age, String group, int course) {
        super(firstName, secondName, age);
        this.group = group;
        this.course = course;
    }
    
    public void addSubject(String name, int grade) {
        this.subjects.add(new Subject(name, grade));
    }

    public SortedSet<Subject> getSubjects() {
        return subjects;
    }
    
    public double getAverageGrade() {
        if (subjects.isEmpty()) {
            return 0.0;
        }
        return subjects.stream()
                       .mapToInt(Subject::getGrade)
                       .average()
                       .orElse(0.0);
    }

    public String getGroup() { return group; }
    public int getCourse() { return course; }
    public void setGroup(String group) { this.group = group; }
    public void setCourse(int course) { this.course = course; }
}