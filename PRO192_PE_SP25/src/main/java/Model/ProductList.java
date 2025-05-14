/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class ProductList {
    public ArrayList<Product> products;

    public ProductList() {
        products = new ArrayList<>();
        loadFromFile("ProductData.txt");
    }
    
    public ArrayList<Product> getProduct() {
        return products;
    }
    private void loadFromFile(String fileName) {
        int invalidEntries = 0;
        StringBuilder errorLog = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                ProductList.class.getClassLoader().getResourceAsStream(fileName)))) {
            if (br == null) {
                throw new IllegalArgumentException("File not found in resources: " + fileName);
            }
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    errorLog.append("Line ").append(lineNumber).append(": Skipped - Empty line\n");
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    try {
                        
                        Product product = new Product(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),                          
                            Integer.parseInt(parts[3].trim()), 
                            Double.parseDouble(parts[4].trim()),
                            parts[5].trim()                       
                        );
                        products.add(product);
                    } catch (IllegalArgumentException e) {
                        invalidEntries++;
                        errorLog.append("Line ").append(lineNumber).append(": ").append(e.getMessage()).append(" - ").append(line).append("\n");
                        continue;
                    }
                } else {
                    invalidEntries++;
                    errorLog.append("Line ").append(lineNumber).append(": Invalid line format - ").append(line).append("\n");
                    continue;
                }
            }
            if (products.isEmpty() && invalidEntries > 0) {
                throw new IllegalArgumentException("No valid products loaded from file.\nErrors:\n" + errorLog.toString());
            }
            if (invalidEntries > 0) {
                throw new IllegalArgumentException("Skipped " + invalidEntries + " invalid entries in the file.\nErrors:\n" + errorLog.toString());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading file: " + e.getMessage());
        }
    }
    
    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Cannot add null product.");
        products.add(product);
    }

    public void sortByquantity() {
        products.sort((a1, a2) -> Double.compare(a1.getQuantity(), a2.getQuantity()));
    }

    public void deleteOldAlbums() {
        products.removeIf(product -> product.getProductAge() >= 2);
    }
    
    public ArrayList<Product> searchByCriteria(String criterion, String value) {
        ArrayList<Product> result = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Product product : products) {
            if (product == null) continue;
            switch (criterion.toLowerCase()) {
                case "name":
                    if (product.getName().equalsIgnoreCase(value)) {
                        result.add(product);
                    }
                    break;
                case "category":
                    if (product.getCategory().toLowerCase().contains(value.toLowerCase())) {
                        result.add(product);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid search criterion: " + criterion);
            }
        }
        return result;
    }
    
     public void displayAllProduct() {
        if (products.isEmpty()) {
            System.out.println("No albums to display.");
            return;
        }
        System.out.println("\nList of Albums");
        System.out.println("| ProductID      | Name     | Category         |Quanity       |Price     |Date   ");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
