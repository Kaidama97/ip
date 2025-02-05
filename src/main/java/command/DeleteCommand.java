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
     * @throws KaidamaException If there is an error in the task details or format.
     * @throws IOException      If there is an error writing to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {
        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new KaidamaException("Please enter a task to delete");
        }
        int idx = Integer.parseInt(split[1].trim());
        if (idx > tasks.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tasks.getTask(idx);
        tasks.deleteTask(idx);
        Ui.deleteTaskMsg(task, tasks.getTaskCount());
        storage.updateTaskInFile(tasks.getTaskList());
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
