package command;

import exception.KaidamaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

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
