package storage;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

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

}
