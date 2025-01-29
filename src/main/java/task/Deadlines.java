package task;

public class Deadlines extends Task {
    private String deadline;
    public Deadlines(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }

    public Deadlines(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }
    @Override
    public String toStorageString() {
        return "D " + super.toStorageString() + " | " + deadline;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
