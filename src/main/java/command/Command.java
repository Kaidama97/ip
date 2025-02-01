package command;

import exception.KaidamaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public abstract class Command {


    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException, IOException;
    public abstract boolean isExit();
}
