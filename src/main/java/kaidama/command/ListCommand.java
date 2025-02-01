package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException {
        ui.getList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
