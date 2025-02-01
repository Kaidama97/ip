package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.TaskList;
import kaidama.ui.Ui;


/**
 *
 * Represents a command to exit the application.
 * It does not modify the task list or interact with the storage, as its primary purpose is to exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException {

    }


    /**
     * Indicates whether the command is an exit command.
     *
     * @return True, as this command exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
