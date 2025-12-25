public class Main {
    public static void main(String[] args) {

        Member member = new Member(1, "Aruzhan", 20, "Basic");

        Trainer trainer = new Trainer(101, "John", "Fitness", 6);

        WorkoutSession session = new WorkoutSession(5001, member.getName(), trainer, 60);

        System.out.println(member);
        System.out.println(trainer);
        System.out.println(session);

        member.upgrade("Premium");
        session.extend(15);
        session.complete();

        System.out.println("\nAfter updates:");
        System.out.println(member);
        System.out.println(session);
    }
}



