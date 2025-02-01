package kaidama.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private static final DateTimeFormatter STORAGE_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    private LocalDateTime deadline;


    public Deadlines(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }
    public Deadlines(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    private String formatDateForStorage(LocalDateTime date) {
        return date.format(STORAGE_DATE_FORMATTER);

    }

    @Override
    public String toStorageString() {
        return "D " + super.toStorageString() + " | " + formatDateForStorage(this.deadline);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DISPLAY_DATE_FORMATTER) + ")";
    }
}
