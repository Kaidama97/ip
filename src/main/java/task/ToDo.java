package task;


import exception.KaidamaException;

/**
 * Represents a Todo task
 * This class extends the Task class and adds functionality to handle Todo functions.
 */
public class ToDo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     * This constructor is use when the user type a task in the command line
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Constructs a Todo task with the specified completion status and description.
     * This constructor is use when perform read from file operation
     *
     * @param isDone      The completion status of the task (true or false).
     * @param description The description of the task.
     */
    public ToDo(boolean isDone, String description, String priority) {
        super(isDone, description, priority);
    }

    /**
     * Converts the task into a string format suitable for storage.
     *
     * @return A string representation of the task for storage.
     */
    @Override
    public String toStorageString() {
        return "T " + super.toStorageString();
    }
    /**
     * Sets the priority of the task.
     * The priority can be one of the following values: "low", "medium", or "high".
     *
     * @param priority the priority level to set; must be "low", "medium", or "high"
     * @throws KaidamaException if the provided priority is not one of the valid values
     */
    @Override
    public void setPriority(String priority) throws KaidamaException {
        super.setPriority(priority);
    }
    /**
     * Converts the task into a string format suitable for display.
     *
     * @return A string representation of the task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
