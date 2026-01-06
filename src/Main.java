import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Member> members = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== GYM MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Member");
            System.out.println("2. Add Student Member");
            System.out.println("3. Add Premium Member");
            System.out.println("4. View All Members");
            System.out.println("5. Demonstrate Polymorphism");
            System.out.println("6. View Student Members Only");
            System.out.println("7. View Premium Members Only");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> addStudent();
                case 3 -> addPremium();
                case 4 -> viewAll();
                case 5 -> demonstratePolymorphism();
                case 6 -> viewStudentsOnly();
                case 7 -> viewPremiumOnly();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addMember() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        members.add(new Member(id, name, age, "Regular"));
        System.out.println("Member added!");
    }

    static void addStudent() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("University: ");
        String uni = scanner.nextLine();
        members.add(new StudentMember(id, name, age, "Student", uni));
        System.out.println("Student member added!");
    }

    static void addPremium() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Personal trainer (true/false): ");
        boolean trainer = scanner.nextBoolean();
        members.add(new PremiumMember(id, name, age, "Premium", trainer));
        System.out.println("Premium member added!");
    }

    static void viewAll() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        System.out.println("\n===== ALL MEMBERS =====");
        for (Member m : members) {
            System.out.println(m);
        }
    }

    static void demonstratePolymorphism() {
        if (members.isEmpty()) {
            System.out.println("No members to demonstrate.");
            return;
        }
        System.out.println("\n===== POLYMORPHISM DEMONSTRATION =====");
        for (Member m : members) {
            m.workout();
        }
    }

    static void viewStudentsOnly() {
        int count = 0;
        System.out.println("\n===== STUDENT MEMBERS ONLY =====");
        for (Member m : members) {
            if (m instanceof StudentMember sm) {
                count++;
                System.out.println(sm);
                sm.useStudentDiscount();
            }
        }
        if (count == 0) System.out.println("No student members found.");
    }

    static void viewPremiumOnly() {
        int count = 0;
        System.out.println("\n===== PREMIUM MEMBERS ONLY =====");
        for (Member m : members) {
            if (m instanceof PremiumMember pm) {
                count++;
                System.out.println(pm);
                pm.bookTrainer();
            }
        }
        if (count == 0) System.out.println("No premium members found.");
    }
}