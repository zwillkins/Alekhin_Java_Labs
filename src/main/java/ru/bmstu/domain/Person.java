package ru.bmstu.domain;

import ru.bmstu.exception.InvalidPhoneNumberException; 

public class Person {
    private final String firstName;
    private final String secondName;
    private int age;
    private String phone;

    public Person(String firstName, String secondName, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public Person(String firstName, String secondName, int age, String phone) {
        this(firstName, secondName, age); 
        try {
            this.setPhone(phone);
        } catch (InvalidPhoneNumberException e) {
            System.out.println("Предупреждение: " + e.getMessage() + ". Телефон не установлен.");
            this.phone = null;
        }
    }
    

    public String getFirstName() { return firstName; }
    public String getSecondName() { return secondName; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public void setAge(int age) { this.age = age; }


    public void setPhone(String phone) throws InvalidPhoneNumberException {
        if (phone == null || !phone.matches("^[0-9()\\-\\s+]+$")) {
            throw new InvalidPhoneNumberException("Телефонный номер содержит недопустимые символы.");
        }
        if (phone.replaceAll("\\D", "").length() < 7) {
            throw new InvalidPhoneNumberException("Телефонный номер слишком короткий.");
        }
        this.phone = phone;
    }
}