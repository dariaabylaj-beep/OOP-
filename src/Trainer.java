public class Trainer {

    private int trainerId;
    private String name;
    private String specialization;
    private int experience;
    private boolean available;

    public Trainer(int trainerId, String name, String specialization, int experience) {
        this.trainerId = trainerId;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.available = true;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getExperience() {
        return experience;
    }

    public boolean isExperienced() {
        return experience >= 5;
    }

    public boolean canTeach(String workoutType) {
        return specialization.equalsIgnoreCase(workoutType);
    }
}
