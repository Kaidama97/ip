package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.Task;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

import java.io.IOException;


public class DeleteCommand extends Command {

    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new KaidamaException("Please enter a valid task number to delete.");
        }


        int idx = Integer.parseInt(split[1].trim());
        if (idx > tasks.getTaskCount()) {
            throw new KaidamaException("No task found with the given index.");
        }

        Task task = tasks.getTask(idx);
        tasks.deleteTask(idx);
        ui.deleteTaskMsg(task, tasks.getTaskCount());
        storage.writeFile(task.toStorageString());

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
