package ui;

import java.util.Scanner;

import task.Task;
import task.TaskList;


public class Ui {
    private static final String TAB_SEPARATOR = "    ";
    private static final String DIVIDER_LINE = (TAB_SEPARATOR + "_".repeat(60));

    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public static String initMsg() {
        return TAB_SEPARATOR + "Hello! I'm Kaidama.\n"
                + TAB_SEPARATOR + "What can I do for you?";
    }

    public String exitMsg() {
        return DIVIDER_LINE + "\n"
                + TAB_SEPARATOR + "Bye. Hope to see you again soon!";
    }

    public String getList(TaskList list) {
        return TAB_SEPARATOR + "Here are the tasks in your list:\n"
                + list.toString();
    }

    public static String unMarkedMsg(Task task) {
        return TAB_SEPARATOR + "OK, I've marked this task as not done yet:\n"
                + TAB_SEPARATOR + TAB_SEPARATOR + task.toString();
    }

    public static String markedMsg(Task task) {
        return TAB_SEPARATOR + "Nice! I've marked this task as done:\n"
                + TAB_SEPARATOR + TAB_SEPARATOR + task.toString();
    }

    public static String setPriorityMsg(Task task) {
        return TAB_SEPARATOR + "I've set this task's priority level to:\n"
                + TAB_SEPARATOR + TAB_SEPARATOR + task.toString();
    }


    public static String addNewTask(Task task, int taskCount) {
        return TAB_SEPARATOR + "Got it. I've added this task:\n"
                + TAB_SEPARATOR + TAB_SEPARATOR + task.toString() + "\n"
                + TAB_SEPARATOR + "Now you have " + taskCount + " task(s) in the list";
    }

    public String errorMsg(String msg) {
        return TAB_SEPARATOR + "OH NO!!! " + msg;
    }

    public static String deleteTaskMsg(Task task, int taskCount) {
        return TAB_SEPARATOR + "Noted. I've removed this task:\n"
                + TAB_SEPARATOR + TAB_SEPARATOR + task.toString() + "\n"
                + TAB_SEPARATOR + "Now you have " + taskCount + " task(s) in the list";
    }


    public static String printDivider() {
        return DIVIDER_LINE;
    }

    public static String printOutputString(String output) {
        return TAB_SEPARATOR + output;
    }


}

