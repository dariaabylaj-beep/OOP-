public class Main {
    public static void main(String[] args) {

        Member member = new Member(1, "Aruzhan", 20, "Basic");

        Trainer trainer = new Trainer(101, "Ali", "Cardio", 6);

        WorkoutSession session = new WorkoutSession(5001, member.getName(), trainer, 60);

        System.out.println("Is member active? " + member.isActiveMember());
        member.upgradeMembership();

        System.out.println("Is trainer experienced? " + trainer.isExperienced());
        System.out.println("Can trainer teach Cardio? " + trainer.canTeach("Cardio"));

        session.extendSession(15);
        session.completeSession();
    }
}
