package task;


import exception.KaidamaException;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private static final String DEFAULT_PRIORITY = "MEDIUM";
    protected String description;
    protected boolean isDone;
    protected String priority;


    /**
     * Constructs a new Task with the specified description.
     * The task is initially not done.
     * This constructor is called when the user input an action.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.priority = DEFAULT_PRIORITY;
        this.isDone = false;
    }
    /**
     * Constructs a new Task with the specified status and description.
     * This constructor executes when reading task from file.
     *
     * @param isDone The completion status of the task.
     * @param description The description of the task.
     */
    public Task(boolean isDone, String description, String priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public void setDone() {
        this.isDone = true;
    }
    public void setUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }
    /**
     * Sets the priority of the task.
     * The priority must be one of the following values: "LOW", "MEDIUM", or "HIGH".
     * The input is case-insensitive and will be converted to uppercase.
     *
     * @param priority the priority level to set; must be "LOW", "MEDIUM", or "HIGH"
     * @throws KaidamaException if the provided priority is not one of the valid values
     */
    public void setPriority(String priority) throws KaidamaException {
        priority = priority.toUpperCase();
        if (!priority.equals("LOW") && !priority.equals("MEDIUM") && !priority.equals("HIGH")) {
            throw new KaidamaException("Input a correct priority level. LOW, MEDIUM or HIGH");
        }
        this.priority = priority;
    }
    /**
     * Converts the task into a string format suitable for storage.
     *
     * @return A string representation of the task for storage.
     */
    public String toStorageString() {
        StringBuilder out = new StringBuilder();
        out.append("| ");
        if (isDone) {
            out.append("1");
        } else {
            out.append("0");
        }
        out.append(" | ");
        out.append(description);
        out.append(" | ");
        return out.append(priority).toString();
    }

    /**
     * Converts the task into a string format suitable for display.
     *
     * @return A string representation of the task for display.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " Priority: " + this.priority;

    }
}
