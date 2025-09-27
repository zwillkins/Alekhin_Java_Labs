package ru.bmstu.domain;

public class Subject {
    private final String name;
    private final int grade;

    public Subject(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return name + ": " + grade;
    }
}