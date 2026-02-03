package model;

public class PremiumMember extends Member {

    private boolean hasPersonalTrainer;

    public PremiumMember(int memberId, String name, int age, boolean hasPersonalTrainer) {
        super(memberId, name, age, "Premium");
        this.hasPersonalTrainer = hasPersonalTrainer;
    }

    public boolean isHasPersonalTrainer() {
        return hasPersonalTrainer;
    }

    public void setHasPersonalTrainer(boolean hasPersonalTrainer) {
        this.hasPersonalTrainer = hasPersonalTrainer;
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
            System.out.println(name + " booked a personal trainer.");
        } else {
            System.out.println(name + " has no personal trainer.");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Personal Trainer: " + (hasPersonalTrainer ? "Yes" : "No");
    }
}
