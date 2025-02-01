package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.Task;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {

        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new KaidamaException("Please enter a task to mark");
        }
        int idx = Integer.parseInt(split[1].trim());
        if (idx > tasks.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tasks.getTask(idx);
        tasks.setTaskDone(idx);
        Ui.markedMsg(task);
        storage.writeFile(task.toStorageString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
