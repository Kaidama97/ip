package command;

import java.io.IOException;

import exception.KaidamaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * delete command removes a specific task from the task list and updates the storage file.
 */
public class DeleteCommand extends Command {

    private String input;


    /**
     * Constructs a DeleteCommand with the given input.
     *
     * @param input The user input specifying the task to delete.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }


    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks   The list of tasks to which the new task will be deleted from.
     * @param ui      Deals with interactions with the user.
     * @param storage The storage deals with loading tasks from the file and saving tasks in the file.
     * @return A message confirming the deletion of the task.
     * @throws KaidamaException If there is an error in the task details or format.
     * @throws IOException      If there is an error writing to the file.
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
            throw new KaidamaException("Please enter a task to delete");
        }
        int idx = Integer.parseInt(split[1].trim());
        assert tasks.getTaskCount() >= 0 : "task should be more than 0";
        if (idx > tasks.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tasks.getTask(idx);
        tasks.deleteTask(idx);
        storage.updateTaskInFile(tasks.getTaskList());
        return ui.deleteTaskMsg(task, tasks.getTaskCount());
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
