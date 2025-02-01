package kaidama.command;

import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.Task;
import kaidama.task.TaskList;
import kaidama.ui.Ui;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException {
        int count = 1;
        String keyword = input.split(" ")[1].trim();
        ui.printDivider();
        ui.printOutputString("Here are the matching tasks in your list:");
        for (int i = 1; i <= taskList.getTaskCount(); i++) {
            Task task = taskList.getTask(i);
            if(task.getDescription().contains(keyword)) {
                ui.printOutputString(count + "." + task.toString());
                count++;
            }
        }
        ui.printDivider();
    }

}
