/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.*;
import View.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Product;
import view.Menu;

/**
 *
 * @author ADMIN
 */
public class ProductsManagement extends Menu<String>{
    private ProductList productList;
    private Scanner scanner;
    private DateTimeFormatter formatter;

    public ProductsManagement() {
        super("ProductsManagement", new ArrayList<String>() {{
            add("Display all products");
            add("Sort product by quatity");
            add("Search products");
            add("Add new products");
            add("Delete old product ( >= 2 years");
            add("Exit");
        }});
        this.productList = null;
        try {
            this.productList = new ProductList();
        } catch (IllegalArgumentException e) {
            System.out.println("Warning: " + e.getMessage());
            this.productList = new ProductList(); // Create an empty list to continue
        }
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1: 
                productList.displayAllProduct();
                break;
            case 2: 
                productList.sortByquantity();
                productList.displayAllProduct();
                break;
            case 3: 
                searchProduct();
                break;
            case 4: 
                addProduct();
                break;
            case 5: 
                productList.deleteOldAlbums();
                productList.displayAllProduct();
                break;
            case 6: // Quit
                System.out.println("Goodbye!");
                break;
        }
    }
        private void addProduct() {
        System.out.println("\nAdd New Product");
        System.out.print("Enter Product ID: ");
        String productID = scanner.nextLine().trim();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter category : ");
        String category = scanner.nextLine().trim();
        System.out.print("Enter quantiry: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price: ");
        int price = scanner.nextInt();
        System.out.print("Enter Date (dd/MM/yyyy : ");
        String dateAdded = scanner.nextLine().trim();
        
        try {
            Product product = new Product(productID, name, category, quantity,price,dateAdded );
            productList.addProduct(product);
            System.out.println("Product added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
        
    }
   
         
         private void searchProduct() {
        ArrayList<String> searchOptions = new ArrayList<>();
        searchOptions.add("Search By name");
        searchOptions.add("Search By Category");

        Menu<String> searchMenu = new Menu<String>("Search Product Option", searchOptions) {
            @Override
            public void execute(int choice) {
                if (choice == searchOptions.size()) return;

                String criterion = "";
                switch (choice) {
                    case 1: criterion = "name"; break;
                    case 2: criterion = "category"; break;
                }

                System.out.print("Enter value to search for: ");
                String value = scanner.nextLine().trim();

                try {
                    ArrayList<Product> results = productList.searchByCriteria(criterion, value);
                    if (results.isEmpty()) {
                        System.out.println("No product found matching the criteria.");
                    } else {
                        System.out.println("\nSearch Results");
                        System.out.println("| ProductID  | Name      | Category         |Quanity       |Price     |Date   ");
                        for (Product product : results) {
                            System.out.println(product.toString());
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error searching products: " + e.getMessage());
                }
            }
        };
        searchMenu.run();
    }
         

}
