package ru.LAB4.data;
import java.time.LocalDate;
public class Purchase {
    String category;
    double amount;
    LocalDate date;
    public Purchase(String category, double amount, LocalDate date) { this.category = category; this.amount = amount; this.date = date; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
}