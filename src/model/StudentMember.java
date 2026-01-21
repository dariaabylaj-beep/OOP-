package model;

import interfaces.Discountable;

public class StudentMember extends Member implements Discountable {

    private String university;

    public StudentMember(int memberId, String name, int age, String university) {
        super(memberId, name, age, "Student");
        setUniversity(university);
    }

    public void setUniversity(String university) {
        if (university == null || university.trim().isEmpty()) {
            throw new IllegalArgumentException("University cannot be empty");
        }
        this.university = university;
    }

    @Override
    public void workout() {
        System.out.println("Student " + name + " is doing a light workout.");
    }

    @Override
    public String getRole() {
        return "Student Member";
    }

    @Override
    public void applyDiscount() {
        System.out.println(name + " applied student discount.");
    }

    @Override
    public String toString() {
        return super.toString() + ", University: " + university;
    }
}
