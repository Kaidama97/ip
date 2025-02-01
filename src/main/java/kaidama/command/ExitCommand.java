package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}