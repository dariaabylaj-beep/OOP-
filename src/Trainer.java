public class Trainer {
    private int trainerId;
    private String name;
    private String specialization;
    private int experience;

    public Trainer(int trainerId, String name, String specialization, int experience) {
        this.trainerId = trainerId;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
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

    public boolean canTeach(String type) {
        return specialization.equalsIgnoreCase(type);
    }

    @Override
    public String toString() {
        return "Trainer{ID=" + trainerId + ", Name='" + name + "', Specialization='" + specialization + "', Experience=" + experience + "}";
    }
}