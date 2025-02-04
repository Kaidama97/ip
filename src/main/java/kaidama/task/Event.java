package kaidama.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific Events.
 * This class extends the Task class and adds functionality to handle events.
 */
public class Event extends Task {

    private static final DateTimeFormatter STORAGE_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time and end time.
     * This constructor is use when the user type a task in the command line
     *
     * @param description The description of the task.
     * @param from    The start time of the task. Date format: dd/MM/yyyy HHmm
     * @param to      The end time of the task. Date format: dd/MM/yyyy HHmm
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with the specified is the task completed, description, start time and end time
     * This constructor is use when perform read from file operation
     *
     * @param isDone      The completion status of the task (true or false).
     * @param description The description of the task.
     * @param from    The start time of the task. Date format: dd/MM/yyyy HHmm
     * @param to      The end time of the task. Date format: dd/MM/yyyy HHmm
     */
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }


    /**
     * Formats the deadline date into a string suitable for storage.
     *
     * @param date The deadline date to format(dd/MM/yyyy HHmm).
     * @return A string representation of the date in the storage format.
     */
    private String formatDateForStorage(LocalDateTime date) {
        return date.format(STORAGE_DATE_FORMATTER);
    }

    /**
     * Converts the task into a string format suitable for storage.
     *
     * @return A string representation of the task for storage.
     */
    @Override
    public String toStorageString() {
        return "E " + super.toStorageString() + " | " + formatDateForStorage(this.from) + "-" + formatDateForStorage(this.to);
    }

    /**
     * Converts the task into a string format suitable for display.
     *
     * @return A string representation of the task for display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DISPLAY_DATE_FORMATTER) +
                 " " + "to: " + this.to.format(DISPLAY_DATE_FORMATTER) + ")";
    }
}
