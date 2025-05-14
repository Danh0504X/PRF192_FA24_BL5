/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author ADMIN
 */
public class Product {
    private String productID;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private LocalDate dateAdded;

    public Product() {
    }

    public Product(String productID, String name, String category, int quantity, double price, String dateAdded) {
        setProductID(productID);
        setName(name);
        setCategory(category);
        setQuantity(quantity);
        setPrice(price);
        setDateAdded(dateAdded);
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        this.productID = productID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }
        this.category = category;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        this.price = price;
    }
    
    
    
    public void setDateAdded(String dateAdded) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate app = LocalDate.parse(dateAdded, formatter);
            if (app.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date cannot be in the future.");
            }
            this.dateAdded = app;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'dd/MM/yyyy'.");
        }
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

     @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("| %-10s | %-10s | %-20s | %-12s | %-10s | %-10s |",
                productID, name, category,quantity, price, dateAdded.format(formatter));
    }
    
    public int getProductAge() {
        if (dateAdded == null) return -1;
        return Period.between(dateAdded, LocalDate.now()).getYears();
    }
}
    
