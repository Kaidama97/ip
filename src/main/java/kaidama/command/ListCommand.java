package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

import java.io.IOException;

/**
 *
 * Represents a command to display the list of task.
 * It does not modify the task list or interact with the storage, as its primary purpose is to display the list.
 */
public class ListCommand extends Command {

    /**
     * Retrieve the list of tasks. Print the taskList with the ui class.
     *
     * @param taskList   The TaskList contains the task list e.g., it has operations to add/delete tasks in the list.
     * @param ui      The user interface deals with interactions with the user.
     * @param storage The storage to deals with loading tasks from the file and saving tasks in the file.
     * @throws KaidamaException If the input is invalid or the task index is out of bounds.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException {
        ui.getList(taskList);
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
