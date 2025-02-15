package command;

import java.io.IOException;

import exception.KaidamaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to set the priority of a task.
 * This command allows users to update the priority level of a task in the task list.
 */
public class SetPriorityCommand extends Command {
    private String input;

    public SetPriorityCommand(String input) {
        assert input != null : "Input cannot be null";
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws KaidamaException, IOException {
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        String removeCommandFromString = input.replace("set priority ", "");
        String[] splitPriority = removeCommandFromString.split(" ");
        if (splitPriority.length != 2) {
            throw new KaidamaException("Wrong number of arguments");
        }
        int idx = Integer.parseInt(splitPriority[0].trim());
        assert taskList.getTaskCount() >= 0 : "TaskList length should be more than or equal to zero";
        if (idx > taskList.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        String priority = splitPriority[1].trim().toUpperCase();
        Task task = taskList.getTask(idx);
        task.setPriority(priority);
        storage.updateTaskInFile(taskList.getTaskList());
        return ui.setPriorityMsg(task);
    }


    @Override
    public boolean isExit() {
        return false;
    }


}
