package command;

import exception.KaidamaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
