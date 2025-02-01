package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

import java.io.IOException;

public abstract class Command {


    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException, IOException;
    public abstract boolean isExit();
}
