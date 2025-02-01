package main;

import exception.KaidamaException;
import storage.FileHandler;
import storage.Parser;
import task.TaskList;
import task.Task;
import task.ToDos;
import task.Deadlines;
import task.Events;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Kaidama {
    private final String filePath = "./data/main.Kaidama.txt";
    private FileHandler fHandler;
    private TaskList tl;
    private Task task;
    private Scanner sc;

    public void run() {
        sc = new Scanner(System.in);
        tl = new TaskList();
        fHandler = new FileHandler(filePath);

        try {
            for (String line : fHandler.readFile()) {
                tl.addTask(Parser.inputToTask(line));
            }
        } catch (KaidamaException e) {
            Response.errorMsg(String.valueOf(e));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Response.initMsg();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    Response.exitMsg();
                    sc.close();
                    break;
                } else if (input.trim().equals("list")) {
                    Response.getList(tl);
                } else if (input.contains("unmark")) {
                    setUnmark(input);
                    fHandler.updateTaskInFile(tl.getTaskList());
                } else if (input.contains("mark")) {
                    setMark(input);
                    fHandler.updateTaskInFile(tl.getTaskList());
                } else if (input.contains("delete")) {
                    deleteTask(input);
                    fHandler.updateTaskInFile(tl.getTaskList());
                } else {
                    if (input.contains("todo")) {
                        setTodo(input);

                    } else if (input.contains("deadline")) {
                        setDeadLine(input);

                    } else if (input.contains("event")) {
                        setEvent(input);
                    } else {
                        throw new KaidamaException("please enter a valid input (mark, unmark, todo, deadline or event only)");
                    }
                    fHandler.writeFile(task.toStorageString());
                    tl.addTask(task);
                    Response.addNewTask(task, tl.getTaskCount());
                }
            } catch (KaidamaException e) {
                Response.errorMsg(String.valueOf(e));
            } catch (IOException e) {
                Response.errorMsg(e.getMessage());
            }
        }

    }

    public static void main(String[] args) throws KaidamaException {
        new Kaidama().run();
    }

    private void setUnmark(String input) throws KaidamaException {
        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new KaidamaException("Please enter a task to unmark");
        }
        int idx = Integer.parseInt(split[1].trim());
        if (idx > tl.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tl.getTask(idx);
        tl.setTaskUndone(idx);
        Response.unMarkedMsg(task);
    }

    private void setMark(String input) throws KaidamaException {
        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new KaidamaException("Please enter a task to mark");
        }
        int idx = Integer.parseInt(split[1].trim());
        if (idx > tl.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tl.getTask(idx);
        tl.setTaskDone(idx);
        Response.markedMsg(task);
    }

    private void deleteTask(String input) throws KaidamaException {
        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new KaidamaException("Please enter a task to delete");
        }
        int idx = Integer.parseInt(split[1].trim());
        if (idx > tl.getTaskCount()) {
            throw new KaidamaException("No task found");
        }
        Task task = tl.getTask(idx);
        tl.deleteTask(idx);
        Response.deleteTaskMsg(task, tl.getTaskCount());
    }

    private void setTodo(String input) throws KaidamaException {
        if (input.split(" ").length == 1) {
            throw new KaidamaException("Please enter a description of the todo task");
        }
        task = new ToDos(input.replace("todo ", ""));

    }

    private void setDeadLine(String input) throws KaidamaException {
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
                throw new KaidamaException("Invalid date format. Please use yyyy-MM-dd.");
            }
        } catch (DateTimeParseException e) {
            throw new KaidamaException("Oh no wrong date format!");
        }
    }

    private void setEvent(String input) throws KaidamaException {

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
        //task = new Events(split[0].trim(), toSplit[0].trim(), toSplit[1].trim());

    }


}
