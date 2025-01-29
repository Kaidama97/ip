package handler;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

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
            return new Deadlines(isDone, tokens[2].trim(), tokens[3].trim());
        } else {
            String[] time = tokens[3].split("-");
            return new Events(isDone, tokens[2].trim(), time[0].trim(), time[1].trim());
        }
    }
}
