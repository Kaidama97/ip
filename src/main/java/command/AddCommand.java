package command;

import java.io.IOException;
import java.time.LocalDateTime;
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

    private static final String MISSING_TODO_DESCRIPTION_ERROR = "Please enter a description of the todo task.";
    private static final String MISSING_DEADLINE_DESCRIPTION_ERROR = "Please enter a description of the deadline.";
    private static final String MISSING_DEADLINE_DATE_ERROR = "Please enter the due date of the task.";
    private static final String MISSING_EVENT_DESCRIPTION_ERROR = "Please enter a description of the event.";
    private static final String MISSING_EVENT_DATES_ERROR = "Please enter the event dates of the task.";
    private static final String MISSING_EVENT_START_TIME_ERROR = "Please enter a start time of the event.";
    private static final String MISSING_EVENT_END_TIME_ERROR = "Please enter an end time of the event.";
    private static final String INVALID_DATE_FORMAT_ERROR = "Invalid date format. Please use %s.";
    private static final String WRONG_DATE_FORMAT_ERROR = "Oh no! Wrong date format!";
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
     * @throws AssertionError if tasks, ui, and storage is null.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        if (input.startsWith("todo")) {
            setTodo();
        } else if (input.startsWith("deadline")) {
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
        String[] splitTaskInput = input.trim().split(" ");
        if (splitTaskInput.length == 1) {
            throw new KaidamaException(MISSING_TODO_DESCRIPTION_ERROR);
        }
        task = new ToDo(input.replace("todo ", ""));

    }

    /**
     * Sets deadline based on the user input.
     *
     * @throws KaidamaException If the description or due date of the Deadline task is invalid.
     */
    private void setDeadLine() throws KaidamaException {
        String[] splitTaskInput = input.trim().split(" ", 2);
        String[] split = splitTaskInput[1].trim().split("/by ");
        String[] dateSplit;
        if (split[0].isEmpty()) {
            throw new KaidamaException(MISSING_DEADLINE_DESCRIPTION_ERROR);
        }
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new KaidamaException(MISSING_DEADLINE_DATE_ERROR);
        }

        dateSplit = split[1].split(" ");
        task = new Deadline(split[0].trim(), parseAndValidateDate(dateSplit, split[1], "dd/MM/yyyy HHmm"));
    }

    /**
     * Sets event based on the user input.
     *
     * @throws KaidamaException If the description, start time, or end time of the Event task is invalid.
     */
    private void setEvent() throws KaidamaException {
        String[] splitTaskInput = input.trim().split(" ", 2);
        String[] split = splitTaskInput[1].trim().split("/from ");

        isValidEventInput(split);

        String[] toSplit = split[1].trim().split("/to ");
        String[] startDate = toSplit[0].trim().split(" ");
        String[] endDate = toSplit[1].trim().split(" ");

        task = new Event(
                split[0].trim(),
                parseAndValidateDate(startDate, toSplit[0].trim(), "dd/MM/yyyy HHmm"),
                parseAndValidateDate(endDate, toSplit[1].trim(), "dd/MM/yyyy HHmm")
        );

    }

    private boolean isValidEventInput(String[] split) throws KaidamaException {
        if (split[0].trim().isEmpty()) {
            throw new KaidamaException(MISSING_EVENT_DESCRIPTION_ERROR);
        }
        if (split.length == 1) {
            throw new KaidamaException(MISSING_EVENT_DATES_ERROR);
        }
        String[] toSplit = split[1].split("/to ");
        if (toSplit[0].trim().isEmpty()) {
            throw new KaidamaException(MISSING_EVENT_START_TIME_ERROR);
        }
        if (toSplit.length == 1 || toSplit[1].trim().isEmpty()) {
            throw new KaidamaException(MISSING_EVENT_END_TIME_ERROR);
        }
        return true;
    }

    /**
     * Parses and validates a date string.
     *
     * @param dateSplit The date string to parse.
     * @param dateFormat The expected date format.
     * @return The parsed date string.
     * @throws KaidamaException If the date string is invalid.
     */
    private LocalDateTime parseAndValidateDate(String[] dateSplit, String date, String dateFormat)
            throws KaidamaException {
        try {
            if (Parser.isDateFormat(dateSplit[0].trim())) {
                return Parser.parseDate(date);
            } else {
                throw new KaidamaException(String.format(INVALID_DATE_FORMAT_ERROR, dateFormat));
            }
        } catch (DateTimeParseException e) {
            throw new KaidamaException(WRONG_DATE_FORMAT_ERROR);
        }
    }
}
