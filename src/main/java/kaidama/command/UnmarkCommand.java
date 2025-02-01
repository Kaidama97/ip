package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.Task;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {
        String[] split = this.input.split(" ");
        if (split.length == 1) {
            throw new KaidamaException("Please enter a task to unmark");
        }
        int idx = Integer.parseInt(split[1].trim());
        if (idx > tasks.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tasks.getTask(idx);
        tasks.setTaskUndone(idx);
        ui.unMarkedMsg(task);
        storage.writeFile(task.toStorageString());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
