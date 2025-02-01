package kaidama.parser;


import kaidama.command.*;
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

    public static Task inputToTask(String input) {
        String[] tokens = input.split("\\|");
        boolean isDone = false;
        if (tokens[1].trim().equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
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
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public static boolean isDateFormat(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;

        }
    }

    public static Command parseCommand(String input) throws KaidamaException {
        if(input.contains("find")) {
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
        } else if (input.contains("deadline") || input.contains("todo") || input.contains("event")) {
            return new AddCommand(input);
        } else {
            throw new KaidamaException("please enter a valid input (mark, unmark, todo, deadline or event only)");
        }
    }
}


