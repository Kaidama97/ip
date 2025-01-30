package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Events extends Task {

    private LocalDateTime from;
    private LocalDateTime to;
    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Events(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        return "E " + super.toStorageString() + " | " + from + "-" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
