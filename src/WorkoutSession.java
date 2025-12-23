public class WorkoutSession {

    private int sessionId;
    private String memberName;
    private Trainer trainer;
    private int duration; // minutes
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

    public int getDuration() {
        return duration;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void extendSession(int extraMinutes) {
        duration += extraMinutes;
        System.out.println("Session extended by " + extraMinutes + " minutes.");
    }

    public void completeSession() {
        completed = true;
        System.out.println("Workout session completed.");
    }
}
