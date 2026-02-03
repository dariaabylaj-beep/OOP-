package model;

public abstract class Member {
    protected int memberId;
    protected String name;
    protected int age;
    protected String membershipType;

    public Member(int memberId, String name, int age, String membershipType) {
        setMemberId(memberId);
        setName(name);
        setAge(age);
        this.membershipType = membershipType;
    }

    public void setMemberId(int memberId) {
        if (memberId <= 0) {
            throw new IllegalArgumentException("Member ID must be positive");
        }
        this.memberId = memberId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
        this.age = age;
    }

    public abstract void workout();
    public abstract String getRole();

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

    @Override
    public String toString() {
        return "[" + getRole() + "] ID: " + memberId +
                ", Name: " + name +
                ", Age: " + age +
                ", Type: " + membershipType;
    }
}
