public class Member {

    private int memberId;
    private String name;
    private int age;
    private String membershipType;
    private boolean active;

    public Member(int memberId, String name, int age, String membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.membershipType = membershipType;
        this.active = true;
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

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void upgrade(String newType) {
        this.membershipType = newType;
    }

    public void cancelMembership() {
        this.active = false;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", membershipType='" + membershipType + '\'' +
                ", active=" + active +
                '}';
    }
}

