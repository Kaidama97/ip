package command;

import exception.KaidamaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;


/**
 * Represents a command to display the list of task.
 * It does not modify the task list or interact with the storage, as its primary purpose is to display the list.
 */
public class ListCommand extends Command {

    /**
     * Retrieve the list of tasks. Print the taskList with the ui class.
     *
     * @param tasks   The TaskList contains the task list e.g., it has operations to add/delete tasks in the list.
     * @param ui      The user interface deals with interactions with the user.
     * @param storage The storage to deals with loading tasks from the file and saving tasks in the file.
     * @return A formatted list of string containing the list of tasks.
     * @throws KaidamaException If the input is invalid or the task index is out of bounds.
     * @throws AssertionError if tasks, ui, and storage is null.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        return ui.getList(tasks);
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
