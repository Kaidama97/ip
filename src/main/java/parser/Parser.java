package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.SetPriorityCommand;
import command.UnmarkCommand;
import exception.KaidamaException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


/**
 * The Parser class is responsible for parsing user input and converting it into
 * commands and tasks.
 */
public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String TASK_DONE_FLAG = "1"; // Constant for task done status
    private static final String INVALID_INPUT_ERROR =
            "Please enter a valid input (mark, unmark, todo, deadline or event only)";
    /**
     * Converts a string representation of a task into a Task object.
     * Handle inputs from file
     *
     * @param input The string representation of the task from file.
     * @return A Task(Todo, Deadline, Event) object corresponding to the input string.
     * @throws AssertionError If input from file does not start with T or D or E.
     */
    public static Task inputToTask(String input) throws KaidamaException {
        assert input.startsWith("T") || input.startsWith("D") || input.startsWith("E")
                : "Input must start with either 'T' or 'D' or 'E'";
        String[] tokens = input.split("\\|");
        boolean isDone = tokens[1].trim().equals(TASK_DONE_FLAG);
        if (input.startsWith("T")) {
            return new ToDo(isDone, tokens[2].trim(), tokens[3].trim());
        } else if (input.startsWith("D")) {
            return new Deadline(isDone, tokens[2].trim(), parseDate(tokens[4].trim()), tokens[3].trim());
        } else if (input.startsWith("E")) {
            String[] time = tokens[4].split("-");
            return new Event(isDone, tokens[2].trim(), Parser.parseDate(time[0].trim()),
                    Parser.parseDate(time[1].trim()), tokens[3].trim());
        }
        throw new KaidamaException("Error reading from file");
    }

    /**
     * Parses a date string into a LocalDateTime object.
     *
     * @param date The date string to parse.
     * @return A LocalDateTime object representing the parsed date format (dd/MM/yyyy HHmm).
     */
    public static LocalDateTime parseDate(String date) throws KaidamaException {
        try {
            return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new KaidamaException("Date time format should follow: dd/MM/yyyy HHmm");
        }
    }


    /**
     * Checks if a string is in the correct date format (dd/MM/yyyy).
     *
     * @param dateString The string to check.
     * @return True if the string is in the correct date format, false otherwise.
     */
    public static boolean isDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;

        }
    }

    /**
     * Parses a user input string into a Command object. Either bye, list, mark, set priority,
     * unmark, delete,deadline, todo or event.
     *
     * @param input The user input string.
     * @return A Command object corresponding to the input.
     * @throws KaidamaException If the input is invalid or unrecognized.
     */
    public static Command parseCommand(String input) throws KaidamaException {
        input = input.toLowerCase();
        if (input.contains("find")) {
            return new FindCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.trim().equals("list")) {
            return new ListCommand();
        } else if (input.contains("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.contains("mark")) {
            return new MarkCommand(input);
        } else if (input.contains("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("deadline") || input.startsWith("todo") || input.startsWith("event")) {
            return new AddCommand(input);
        } else if (input.contains("set priority")) {
            return new SetPriorityCommand(input);
        } else {
            throw new KaidamaException(INVALID_INPUT_ERROR);
        }
    }
}


