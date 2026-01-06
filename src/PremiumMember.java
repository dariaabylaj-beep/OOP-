public class PremiumMember extends Member {
    private boolean hasPersonalTrainer;

    public PremiumMember(int memberId, String name, int age, String membershipType, boolean hasPersonalTrainer) {
        super(memberId, name, age, membershipType);
        this.hasPersonalTrainer = hasPersonalTrainer;
    }

    public boolean hasPersonalTrainer() {
        return hasPersonalTrainer;
    }

    @Override
    public void workout() {
        System.out.println("Premium member " + name + " is training with a personal trainer.");
    }

    @Override
    public String getRole() {
        return "Premium Member";
    }

    public void bookTrainer() {
        if (hasPersonalTrainer) {
            System.out.println(name + " has booked a personal trainer.");
        } else {
            System.out.println(name + " does not have a personal trainer.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Personal Trainer: " + (hasPersonalTrainer ? "Yes" : "No");
    }
}