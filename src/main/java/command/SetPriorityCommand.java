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

    /**
     * Constructs an SetPriorityCommand with the given input.
     *
     * @param input The user input specifying the task number and priority level.
     * @throws AssertionError if input must is null.
     */
    public SetPriorityCommand(String input) {
        assert input != null : "Input cannot be null";
        this.input = input;
    }

    /**
     * Executes the command to change a task's priority level.
     * Retrieve task from list and change task priority
     *
     * @param taskList   The TaskList contains the task list e.g. it has operations to add/delete tasks in the list.
     * @param ui      The user interface deals with interactions with the user.
     * @param storage The storage to deals with loading tasks from the file and saving tasks in the file.
     * @return A message confirming the completion of the task.
     * @throws KaidamaException If the input is invalid or the task index is out of bounds.
     * @throws IOException      If there is an error updating the storage file.
     * @throws AssertionError if tasks, ui, and storage is null.
     * @throws AssertionError If TaskList is less than 0.
     */
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
