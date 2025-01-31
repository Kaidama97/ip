package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private String outputDateFormat(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")).toString();
    }

    @Override
    public String toStorageString() {
        return "D " + super.toStorageString() + " | " + outputDateFormat(this.deadline);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
