import java.util.ArrayList;

public class Member {
    protected int memberId;
    protected String name;
    protected int age;
    protected String membershipType;

    public Member(int memberId, String name, int age, String membershipType) {
        this.memberId = memberId;
        this.name = name;
        setAge(age);
        this.membershipType = membershipType;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Age must be positive!");
        }
    }

    public void workout() {
        System.out.println(name + " is doing a general workout.");
    }

    public String getRole() {
        return "Gym Member";
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] ID: " + memberId +
                ", Name: " + name +
                ", Age: " + age +
                ", Type: " + membershipType;
    }
}