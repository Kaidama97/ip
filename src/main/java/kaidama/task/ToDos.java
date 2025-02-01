package kaidama.task;

public class ToDos extends Task {

    /**
     * Constructs a Todo task with the specified description.
     * This constructor is use when the user type a task in the command line
     *
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description);
    }


    /**
     * Constructs a Todo task with the specified completion status and description.
     * This constructor is use when perform read from file operation
     *
     * @param isDone      The completion status of the task (true or false).
     * @param description The description of the task.
     */
    public ToDos(boolean isDone, String description) {
        super(isDone, description);
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
     * Converts the task into a string format suitable for display.
     *
     * @return A string representation of the task for display.
     */
    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }
}
