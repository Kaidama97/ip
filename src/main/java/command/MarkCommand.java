package command;

import java.io.IOException;

import exception.KaidamaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class MarkCommand extends Command {
    private final String input;

    /**
     * Constructs an MarkCommand with the given input.
     *
     * @param input The user input specifying the task to unmark.
     * @throws AssertionError if input must is null.
     */
    public MarkCommand(String input) {
        assert input != null : "Input cannot be null";
        this.input = input;
    }

    /**
     * Executes the command to mark a task as done. Retrieve task from list and mark task as done
     *
     * @param tasks   The TaskList contains the task list e.g., it has operations to add/delete tasks in the list.
     * @param ui      The user interface deals with interactions with the user.
     * @param storage The storage to deals with loading tasks from the file and saving tasks in the file.
     * @return A message confirming the completion of the task.
     * @throws KaidamaException If the input is invalid or the task index is out of bounds.
     * @throws IOException      If there is an error updating the storage file.
     * @throws AssertionError if tasks, ui, and storage is null.
     * @throws AssertionError If TaskList is less than 0.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new KaidamaException("Please enter a task to mark");
        }
        int idx = Integer.parseInt(split[1].trim());
        assert tasks.getTaskCount() >= 0 : "TaskList length should be more than or equal to zero";
        if (idx > tasks.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tasks.getTask(idx);
        tasks.setTaskDone(idx);
        storage.updateTaskInFile(tasks.getTaskList());
        return ui.markedMsg(task);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return False, as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
