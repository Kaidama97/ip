package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.Task;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done.
 */
public class MarkCommand extends Command {
    private final String input;

    /**
     * Constructs an MarkCommand with the given input.
     *
     * @param input The user input specifying the task to unmark.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to mark a task as done. Retrieve task from list and mark task as done
     *
     * @param tasks   The TaskList contains the task list e.g., it has operations to add/delete tasks in the list.
     * @param ui      The user interface deals with interactions with the user.
     * @param storage The storage to deals with loading tasks from the file and saving tasks in the file.
     * @throws KaidamaException If the input is invalid or the task index is out of bounds.
     * @throws IOException      If there is an error updating the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {

        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new KaidamaException("Please enter a task to mark");
        }
        int idx = Integer.parseInt(split[1].trim());
        if (idx > tasks.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tasks.getTask(idx);
        tasks.setTaskDone(idx);
        Ui.markedMsg(task);
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
