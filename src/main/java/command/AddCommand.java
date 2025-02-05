package command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import exception.KaidamaException;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * The AddCommand class represents a command Todo, Deadline, and Event. Add task into the TaskList
 */
public class AddCommand extends Command {
    private String input;
    private Task task;

    /**
     * Constructs an AddCommand object with the user input.
     *
     * @param input The user input containing the task details.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes command to add a task to the task list.
     *
     * @param tasks   The list of tasks to which the new task will be added.
     * @param ui      Deals with interactions with the user.
     * @param storage The storage deals with loading tasks from the file and saving tasks in the file.
     * @return A message confirming the addition of the task.
     * @throws KaidamaException If there is an error in the task details or format.
     * @throws IOException      If there is an error writing to the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {
        if (input.contains("todo")) {
            setTodo();
        } else if (input.contains("deadline")) {
            setDeadLine();
        } else {
            setEvent();
        }
        storage.writeFile(task.toStorageString());
        tasks.addTask(task);
        return Ui.addNewTask(task, tasks.getTaskCount());
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
     * Sets todo task based on the user input.
     *
     * @throws KaidamaException If the description of the Todo task is empty.
     */
    private void setTodo() throws KaidamaException {
        if (input.split(" ").length == 1) {
            throw new KaidamaException("Please enter a description of the todo task");
        }
        task = new ToDo(input.replace("todo ", ""));

    }

    /**
     * Sets deadline based on the user input.
     *
     * @throws KaidamaException If the description or due date of the Deadline task is invalid.
     */
    private void setDeadLine() throws KaidamaException {
        input = input.replace("deadline ", "");
        String[] split = input.split("/by ");
        String[] dateSplit;
        if (split[0].isEmpty()) {
            throw new KaidamaException("Please enter a description of the deadline");
        } else if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new KaidamaException("Please enter the due date of the task");
        }

        dateSplit = split[1].split(" ");

        try {
            String date = dateSplit[0];
            if (Parser.isDateFormat(date)) {
                task = new Deadline(split[0].trim(), Parser.parseDate(split[1].trim()));
            } else {
                throw new KaidamaException("Invalid date format. Please use dd/MM/yyyy.");
            }
        } catch (DateTimeParseException e) {
            throw new KaidamaException("Oh no wrong date format!");
        }
    }

    /**
     * Sets event based on the user input.
     *
     * @throws KaidamaException If the description, start time, or end time of the Event task is invalid.
     */
    private void setEvent() throws KaidamaException {

        String msg = input.replace("event ", "");
        String[] split = msg.split("/from ");
        if (split[0].trim().isEmpty()) {
            throw new KaidamaException("Please enter a description of the event");
        }
        if (split.length == 1) {
            throw new KaidamaException("Please enter the event dates of the task");
        }
        String[] toSplit = split[1].split("/to ");
        if (toSplit[0].trim().isEmpty()) {
            throw new KaidamaException("Please enter a start time of the event");
        }
        if (toSplit.length == 1 || toSplit[1].trim().isEmpty()) {
            throw new KaidamaException("Please enter a end time of the event");
        }

        try {
            String[] dateFromSplit = toSplit[0].trim().split(" ");
            String[] dateToSplit = toSplit[1].trim().split(" ");

            if (Parser.isDateFormat(dateFromSplit[0]) && Parser.isDateFormat(dateToSplit[0])) {
                task = new Event(split[0].trim(), Parser.parseDate(toSplit[0].trim()),
                        Parser.parseDate(toSplit[1].trim()));
            } else {
                throw new KaidamaException("Invalid date format. Please use yyyy-MM-dd.");
            }
        } catch (DateTimeParseException e) {
            throw new KaidamaException("Oh no wrong date format!");
        }

    }
}
