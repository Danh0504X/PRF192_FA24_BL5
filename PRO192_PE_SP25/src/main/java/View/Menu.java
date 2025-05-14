package view;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu<T> {
    protected String title;
    protected ArrayList<T> options;
    protected Scanner scanner;

    public Menu(String title, ArrayList<T> options) {
        this.title = title;
        this.options = options;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        System.out.println("\n" + title);
        System.out.println("--------------------------------");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println("--------------------------------");
        System.out.print("Enter your choice: ");
    }

    public int getChoice() {
        display();
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > options.size()) {
                System.out.println("Invalid choice. Please choose between 1 and " + options.size() + ".");
                return -1;
            }
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    public abstract void execute(int choice);

    public void run() {
        while (true) {
            int choice = getChoice();
            if (choice == -1) continue;
            execute(choice);
            if (choice == options.size()) break;
        }
    }
}