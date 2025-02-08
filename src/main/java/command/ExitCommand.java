package command;

import exception.KaidamaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;


/**
 * Represents a command to exit the application.
 * It does not modify the task list or interact with the storage, as its primary purpose is to exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        return ui.exitMsg();
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
