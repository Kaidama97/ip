package command;

import exception.KaidamaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks with keywords from the task list.
 * find command list down all the words that contained user-defined substring.
 */
public class FindCommand extends Command {
    private static final String NO_KEYWORD_ERROR = "Please provide a keyword to search for.";
    private static final String NO_MATCHING_TASKS_MESSAGE = "No matching tasks found.";
    private static final String MATCHING_TASKS_HEADER = "Here are the matching tasks in your list:";
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }
    /**
     * Indicates whether the command is an exit command.
     *
     * @return False, as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     *
     * @param tasks The list of tasks to search through.
     * @param ui       Handles input and output with the user.
     * @param storage  Manages loading and saving of tasks.
     * @return A formatted string containing the matching tasks.
     * @throws KaidamaException If no keyword is provided or the input format is incorrect.
     * @throws AssertionError if tasks, ui, and storage is null.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        int count = 1;
        StringBuilder outputStr = new StringBuilder();
        String[] inputSplit = input.split(" ");
        if (inputSplit.length < 2) {
            throw new KaidamaException(NO_KEYWORD_ERROR);
        }
        String keyword = input.split(" ")[1].trim();
        outputStr.append(ui.printOutputString(MATCHING_TASKS_HEADER)).append("\n");
        for (int i = 1; i <= tasks.getTaskCount(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                outputStr.append(ui.printOutputString(count + "." + task.toString())).append("\n");
                count++;
            }
        }
        if (count == 1) {
            return ui.printOutputString(NO_MATCHING_TASKS_MESSAGE);
        }
        return outputStr.toString();
    }

}
