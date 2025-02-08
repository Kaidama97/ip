package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * The class allow user to add, delete, mark/unmark task as completed, get size of list and get task
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     * This constructor will be used when read task from file
     *
     * @param taskList The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void setTaskDone(int index) {
        getTask(index).setDone();
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task to mark as undone.
     */
    public void setTaskUndone(int index) {
        getTask(index).setUndone();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index - 1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Converts the task into a string format suitable for display.
     *
     * @return A string representation of the task for display.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        int count;
        String str = "";
        for (int i = 0; i < taskList.size(); i++) {
            count = (i + 1);
            out.append("    ").append(count).append(".");
            out.append(taskList.get(i).toString()).append("\n");
        }
        return out.toString();
    }

}
