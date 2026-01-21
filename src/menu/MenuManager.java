package menu;

import model.*;
import interfaces.Discountable;
import exception.InvalidInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Member> members = new ArrayList<>();

    @Override
    public void displayMenu() {
        System.out.println("\n===== GYM MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student Member");
        System.out.println("2. Add Premium Member");
        System.out.println("3. View All Members");
        System.out.println("4. Polymorphism Demo");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> addPremium();
                    case 3 -> viewAll();
                    case 4 -> demoPolymorphism();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice");
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number!");
            }
        }
    }

    private void addStudent() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("University: ");
            String uni = scanner.nextLine();

            StudentMember sm = new StudentMember(id, name, age, uni);
            members.add(sm);

            System.out.println("Student member added!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addPremium() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Has personal trainer (true/false): ");
            boolean trainer = Boolean.parseBoolean(scanner.nextLine());

            PremiumMember pm = new PremiumMember(id, name, age, trainer);
            members.add(pm);

            System.out.println("Premium member added!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAll() {
        if (members.isEmpty()) {
            System.out.println("No members.");
            return;
        }
        for (Member m : members) {
            System.out.println(m);
        }
    }

    private void demoPolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        for (Member m : members) {
            m.workout();

            if (m instanceof Discountable d) {
                d.applyDiscount();
            }
        }
    }
}
