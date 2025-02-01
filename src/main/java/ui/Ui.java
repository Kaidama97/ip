package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {
    private final static String TAB_SEPARATOR = "    ";
    private final static String DIVIDER_LINE = (TAB_SEPARATOR + "_".repeat(60));

    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public static void initMsg() {
        System.out.println(TAB_SEPARATOR + "Hello! I'm Kaidama.");
        System.out.println(TAB_SEPARATOR + "What can I do for you?");

    }

    public void exitMsg() {
        System.out.println(DIVIDER_LINE);
        System.out.println(TAB_SEPARATOR + "Bye. Hope to see you again soon!");
        System.out.println(DIVIDER_LINE);
    }

    public void getList(TaskList list) {
        System.out.println(DIVIDER_LINE);
        System.out.println(TAB_SEPARATOR + "Here are the tasks in your list:");
        System.out.print(list.toString());
        System.out.println(DIVIDER_LINE);
    }

    public static void unMarkedMsg(Task task) {
        System.out.println(DIVIDER_LINE);
        System.out.println(TAB_SEPARATOR + "OK, I've marked this task as not done yet:");
        System.out.println(TAB_SEPARATOR + TAB_SEPARATOR + task.toString());
        System.out.println(DIVIDER_LINE);
    }

    public static void markedMsg(Task task) {
        System.out.println(DIVIDER_LINE);
        System.out.println(TAB_SEPARATOR + "Nice! I've marked this task as done:");
        System.out.println(TAB_SEPARATOR + TAB_SEPARATOR + task.toString());
        System.out.println(DIVIDER_LINE);
    }

    public static void addNewTask(Task task, int taskCount) {
        System.out.println(DIVIDER_LINE);
        System.out.println(TAB_SEPARATOR + "Got it. I've added this task:");
        System.out.println(TAB_SEPARATOR + TAB_SEPARATOR + task.toString());
        System.out.println(TAB_SEPARATOR + "Now you have " + taskCount + " task in the list");
        System.out.println(DIVIDER_LINE);
    }

    public void errorMsg(String msg) {
        System.out.println(DIVIDER_LINE);
        System.out.println(TAB_SEPARATOR + "OH NO!!! " + msg);
        System.out.println(DIVIDER_LINE);
    }

    public static void deleteTaskMsg(Task task, int taskCount) {
        System.out.println(DIVIDER_LINE);
        System.out.println(TAB_SEPARATOR + "Noted. I've removed this task:");
        System.out.println(TAB_SEPARATOR + TAB_SEPARATOR + task.toString());
        System.out.println(TAB_SEPARATOR + "Now you have " + taskCount + " task in the list");
        System.out.println(DIVIDER_LINE);
    }


}

