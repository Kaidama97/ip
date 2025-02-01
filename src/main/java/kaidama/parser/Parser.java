package kaidama.parser;


import kaidama.command.Command;
import kaidama.command.AddCommand;
import kaidama.command.ListCommand;
import kaidama.command.DeleteCommand;
import kaidama.command.MarkCommand;
import kaidama.command.UnmarkCommand;
import kaidama.command.ExitCommand;

import kaidama.exception.KaidamaException;
import kaidama.task.Deadlines;
import kaidama.task.Events;
import kaidama.task.Task;
import kaidama.task.ToDos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String TASK_DONE_FLAG = "1"; // Constant for task done status

    public static Task inputToTask(String input) {
        String[] tokens = input.split("\\|");
        boolean isDone = tokens[1].trim().equals(TASK_DONE_FLAG);
        if (input.startsWith("T")) {
            return new ToDos(isDone, tokens[2].trim());
        } else if (input.startsWith("D")) {
            return new Deadlines(isDone, tokens[2].trim(), parseDate(tokens[3].trim()));
        } else {
            String[] time = tokens[3].split("-");
            return new Events(isDone, tokens[2].trim(), Parser.parseDate(time[0].trim()), Parser.parseDate(time[1].trim()));
        }
    }

    public static LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    public static boolean isDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;

        }
    }

    public static Command parseCommand(String input) throws KaidamaException {
        input = input.toLowerCase();
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.trim().equals("list")) {
            return new ListCommand();
        } else if (input.contains("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.contains("mark")) {
            return new MarkCommand(input);
        } else if (input.contains("delete")) {
            return new DeleteCommand(input);
        } else if (input.contains("deadline") || input.contains("todo") || input.contains("event")) {
            return new AddCommand(input);
        } else {
            throw new KaidamaException("please enter a valid input (mark, unmark, todo, deadline or event only)");
        }
    }
}


