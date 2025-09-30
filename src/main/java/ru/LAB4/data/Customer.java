package ru.LAB4.data;
import java.util.List;
public class Customer {
    String email;
    List<Purchase> purchases;
    public Customer(String email, List<Purchase> purchases) { this.email = email; this.purchases = purchases; }
    public String getEmail() { return email; }
    public List<Purchase> getPurchases() { return purchases; }
}