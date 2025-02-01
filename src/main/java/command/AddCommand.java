package command;

import exception.KaidamaException;
import parser.Parser;
import storage.Storage;
import task.Deadlines;
import task.Events;
import task.TaskList;
import task.ToDos;
import task.Task;
import ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String input;
    private Task task;

    public AddCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KaidamaException, IOException {
        if(input.contains("todo")) {
            setTodo();
        } else if(input.contains("deadline")) {
            setDeadLine();
        } else {
            setEvent();
        }
        storage.writeFile(task.toStorageString());
        tasks.addTask(task);
        Ui.addNewTask(task, tasks.getTaskCount());
    }
    @Override
    public boolean isExit() {
        return false;
    }

    private void setTodo() throws KaidamaException {
        if (input.split(" ").length == 1) {
            throw new KaidamaException("Please enter a description of the todo task");
        }
        task = new ToDos(input.replace("todo ", ""));

    }

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
                task = new Deadlines(split[0].trim(), Parser.parseDate(split[1].trim()));
            } else {
                throw new KaidamaException("Invalid date format. Please use dd/MM/yyyy.");
            }
        } catch (DateTimeParseException e) {
            throw new KaidamaException("Oh no wrong date format!");
        }
    }

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
                task = new Events(split[0].trim(), Parser.parseDate(toSplit[0].trim()), Parser.parseDate(toSplit[1].trim()));
            } else {
                throw new KaidamaException("Invalid date format. Please use yyyy-MM-dd.");
            }
        } catch (DateTimeParseException e) {
            throw new KaidamaException("Oh no wrong date format!");
        }

    }
}
