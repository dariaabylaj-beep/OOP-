public class WorkoutSession {

    private int sessionId;
    private String memberName;
    private Trainer trainer;
    private int duration;
    private boolean completed;

    public WorkoutSession(int sessionId, String memberName, Trainer trainer, int duration) {
        this.sessionId = sessionId;
        this.memberName = memberName;
        this.trainer = trainer;
        this.duration = duration;
        this.completed = false;
    }

    public int getSessionId() {
        return sessionId;
    }

    public String getMemberName() {
        return memberName;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void extend(int extraMinutes) {
        this.duration += extraMinutes;
    }

    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "WorkoutSession{" +
                "sessionId=" + sessionId +
                ", memberName='" + memberName + '\'' +
                ", trainer=" + trainer.getName() +
                ", duration=" + duration +
                ", completed=" + completed +
                '}';
    }
}
