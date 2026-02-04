package menu;

import dao.MemberDAO;
import exception.InvalidInputException;
import interfaces.Discountable;
import model.Member;
import model.PremiumMember;
import model.StudentMember;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final MemberDAO dao = new MemberDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n===== GYM MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student Member (INSERT)");
        System.out.println("2. Add Premium Member (INSERT)");
        System.out.println("3. View All Members (SELECT)");
        System.out.println("4. View Student Members (Filtered SELECT)");
        System.out.println("5. View Premium Members (Filtered SELECT)");
        System.out.println("6. Get Member by ID (getById)");
        System.out.println("7. Update Student Member (UPDATE)");
        System.out.println("8. Update Premium Member (UPDATE)");
        System.out.println("9. Delete Member (DELETE)");
        System.out.println("10. Search by Name (ILIKE)");
        System.out.println("11. Search by Age Range (BETWEEN)");
        System.out.println("12. Search by Min Age (>=)");
        System.out.println("13. Polymorphism Demo");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    @Override
    public void run() {
        boolean running = true;

        try {
            while (running) {
                displayMenu();

                try {
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1: addStudent(); break;
                        case 2: addPremium(); break;
                        case 3: viewAll(); break;
                        case 4: viewStudents(); break;
                        case 5: viewPremiums(); break;
                        case 6: getById(); break;
                        case 7: updateStudent(); break;
                        case 8: updatePremium(); break;
                        case 9: deleteMember(); break;
                        case 10: searchByName(); break;
                        case 11: searchByAgeRange(); break;
                        case 12: searchByMinAge(); break;
                        case 13: demoPolymorphism(); break;
                        case 0:
                            running = false;
                            System.out.println("Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid number!");
                } catch (InvalidInputException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void addStudent() throws InvalidInputException, SQLException {
        int id = readInt("ID: ");
        String name = readText("Name: ");
        int age = readInt("Age: ");
        String uni = readText("University: ");

        StudentMember sm = new StudentMember(id, name, age, uni);
        boolean ok = dao.insertStudentMember(sm);
        System.out.println(ok ? "Student member added!" : "Insert failed");
    }

    private void addPremium() throws InvalidInputException, SQLException {
        int id = readInt("ID: ");
        String name = readText("Name: ");
        int age = readInt("Age: ");
        boolean trainer = readBoolean("Has personal trainer (true/false): ");

        PremiumMember pm = new PremiumMember(id, name, age, trainer);
        boolean ok = dao.insertPremiumMember(pm);
        System.out.println(ok ? "Premium member added!" : "Insert failed");
    }

    private void viewAll() throws SQLException {
        List<Member> members = dao.getAllMembers();
        printMembers(members);
    }

    private void viewStudents() throws SQLException {
        List<Member> members = dao.getMembersByType("Student");
        printMembers(members);
    }

    private void viewPremiums() throws SQLException {
        List<Member> members = dao.getMembersByType("Premium");
        printMembers(members);
    }

    private void getById() throws InvalidInputException, SQLException {
        int id = readInt("Enter member ID: ");
        Member m = dao.getMemberById(id);

        if (m == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println(m);
    }

    private void updateStudent() throws InvalidInputException, SQLException {
        int id = readInt("Student ID: ");
        Member existing = dao.getMemberById(id);

        if (existing == null) {
            System.out.println("Member not found.");
            return;
        }
        if (!(existing instanceof StudentMember)) {
            System.out.println("This ID is not a Student Member.");
            return;
        }

        String name = readText("New name: ");
        int age = readInt("New age: ");
        String uni = readText("New university: ");

        new StudentMember(id, name, age, uni);

        boolean ok = dao.updateStudentMember(id, name, age, uni);
        System.out.println(ok ? "Updated!" : "Update failed");
    }

    private void updatePremium() throws InvalidInputException, SQLException {
        int id = readInt("Premium ID: ");
        Member existing = dao.getMemberById(id);

        if (existing == null) {
            System.out.println("Member not found.");
            return;
        }
        if (!(existing instanceof PremiumMember)) {
            System.out.println("This ID is not a Premium Member.");
            return;
        }

        String name = readText("New name: ");
        int age = readInt("New age: ");
        boolean trainer = readBoolean("Has personal trainer (true/false): ");

        new PremiumMember(id, name, age, trainer);

        boolean ok = dao.updatePremiumMember(id, name, age, trainer);
        System.out.println(ok ? "Updated!" : "Update failed");
    }

    private void deleteMember() throws InvalidInputException, SQLException {
        int id = readInt("Member ID to delete: ");
        Member existing = dao.getMemberById(id);

        if (existing == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("Found: " + existing);
        String confirm = readText("Type YES to confirm deletion: ");

        if (!confirm.equalsIgnoreCase("YES")) {
            System.out.println("Cancelled.");
            return;
        }

        boolean ok = dao.deleteMember(id);
        System.out.println(ok ? "Deleted!" : "Delete failed");
    }

    private void searchByName() throws InvalidInputException, SQLException {
        String part = readText("Name contains: ");
        List<Member> members = dao.searchByName(part);
        printMembers(members);
    }

    private void searchByAgeRange() throws InvalidInputException, SQLException {
        int min = readInt("Min age: ");
        int max = readInt("Max age: ");

        if (min > max) {
            System.out.println("Min age cannot be bigger than max age.");
            return;
        }

        List<Member> members = dao.searchByAgeRange(min, max);
        printMembers(members);
    }

    private void searchByMinAge() throws InvalidInputException, SQLException {
        int min = readInt("Min age: ");
        List<Member> members = dao.searchByMinAge(min);
        printMembers(members);
    }

    private void demoPolymorphism() throws SQLException {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        List<Member> members = dao.getAllMembers();

        for (Member m : members) {
            m.workout();
            if (m instanceof Discountable) {
                Discountable d = (Discountable) m;
                d.applyDiscount();
            }
        }
    }

    private void printMembers(List<Member> members) {
        if (members.isEmpty()) {
            System.out.println("No members.");
            return;
        }
        for (Member m : members) {
            System.out.println(m);
        }
    }

    private String readText(String prompt) throws InvalidInputException {
        System.out.print(prompt);
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            throw new InvalidInputException("Input cannot be empty");
        }
        return text;
    }

    private int readInt(String prompt) throws InvalidInputException {
        String text = readText(prompt);
        return Integer.parseInt(text);
    }

    private boolean readBoolean(String prompt) throws InvalidInputException {
        String text = readText(prompt);

        if (!text.equalsIgnoreCase("true") && !text.equalsIgnoreCase("false")) {
            throw new InvalidInputException("Enter true or false");
        }

        return Boolean.parseBoolean(text);
    }
}
