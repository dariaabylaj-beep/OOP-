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

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public boolean isActiveMember() {
        return active;
    }

    public void upgradeMembership() {
        if (membershipType.equals("Basic")) {
            membershipType = "Premium";
            System.out.println(name + " upgraded to Premium membership.");
        } else {
            System.out.println(name + " already has Premium membership.");
        }
    }
}
