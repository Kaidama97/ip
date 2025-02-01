package kaidama.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private static final DateTimeFormatter STORAGE_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

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

    private String formatDateForStorage(LocalDateTime date) {
        return date.format(STORAGE_DATE_FORMATTER);
    }

    @Override
    public String toStorageString() {
        return "E " + super.toStorageString() + " | " + formatDateForStorage(this.from) + "-" + formatDateForStorage(this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DISPLAY_DATE_FORMATTER) +
                " to: " + this.to.format(DISPLAY_DATE_FORMATTER) + ")";
    }
}
