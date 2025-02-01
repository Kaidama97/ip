package kaidama.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 * This class extends the Task class and adds functionality to handle deadlines.
 */
public class Deadlines extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadlines task with the specified completion status, description, and deadline.
     * This constructor is use when perform read from file operation
     *
     * @param isDone      The completion status of the task (true or false).
     * @param description The description of the task.
     * @param deadline    The deadline of the task. Date format: dd/MM/yyyy HHmm
     */
    public Deadlines(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    /**
     * Constructs a Deadlines task with the specified description and deadline.
     * This constructor is use when the user type a task in the command line
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task. Date format: dd/MM/yyyy HHmm
     */
    public Deadlines(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    /**
     * Formats the deadline date into a string suitable for storage.
     *
     * @param date The deadline date to format(dd/MM/yyyy HHmm).
     * @return A string representation of the date in the storage format.
     */
    private String outputDateFormat(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")).toString();
    }

    /**
     * Converts the task into a string format suitable for storage.
     *
     * @return A string representation of the task for storage.
     */
    @Override
    public String toStorageString() {
        return "D " + super.toStorageString() + " | " + outputDateFormat(this.deadline);
    }

    /**
     * Converts the task into a string format suitable for display.
     *
     * @return A string representation of the task for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
