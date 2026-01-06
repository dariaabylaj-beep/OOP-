public class StudentMember extends Member {
    private String university;

    public StudentMember(int memberId, String name, int age, String membershipType, String university) {
        super(memberId, name, age, membershipType);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    @Override
    public void workout() {
        System.out.println("Student " + name + " is training with a discount.");
    }

    @Override
    public String getRole() {
        return "Student Member";
    }

    public void useStudentDiscount() {
        System.out.println(name + " uses student discount.");
    }

    @Override
    public String toString() {
        return super.toString() + ", University: " + university;
    }
}

