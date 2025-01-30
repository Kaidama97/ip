package task;

import java.time.LocalDateTime;

public class Deadlines extends Task {
    private LocalDateTime deadline;


    public Deadlines(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }
    public Deadlines(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toStorageString() {
        return "D " + super.toStorageString() + " | " + deadline.toString();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.toString() + ")";
    }
}
