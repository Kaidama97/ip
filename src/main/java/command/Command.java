package command;
import java.io.IOException;

import exception.KaidamaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;



/**
 * Represents an abstract command in the Kaidama application.
 *
 */
public abstract class Command {

    /**
     * Executes the command on the task list, interacting with the user interface and save/read storage(file).
     *
     * @param taskList The list of tasks to perform the command on.
     * @param ui       The user interface that handles interactions with the user.
     * @param storage  The storage that manages saving and loading tasks from the file.
     * @return A message confirming the action of the task.
     * @throws KaidamaException If there is an error in processing the command (e.g., invalid task data).
     * @throws IOException      If there is an error reading from or writing to the storage file.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException,
            IOException;
    /**
     * Determines whether the command signals the application to exit.
     *
     * @return true if the command causes the application to exit, false otherwise.
     */
    public abstract boolean isExit();
}
