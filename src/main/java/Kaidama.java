import java.util.Objects;
import java.util.Scanner;

public class Kaidama {

    public static void main(String[] args) throws KaidamaException {
        TaskList tl = new TaskList();
        Scanner sc = new Scanner(System.in);

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

                    String[] split = input.split(" ");
                    if (split.length == 1) throw new KaidamaException("Please enter a task to unmark");
                    int idx = Integer.parseInt(split[1]);
                    if(idx > tl.getTaskCount()) throw new KaidamaException("No task found");
                    Task task = tl.getTask(idx);
                    tl.setTaskUndone(idx);
                    Response.unMarkedMsg(task);

                } else if (input.contains("mark")) {
                    String[] split = input.split(" ");
                    if (split.length == 1) throw new KaidamaException("Please enter a task to mark");
                    int idx = Integer.parseInt(split[1]);
                    if(idx > tl.getTaskCount()) throw new KaidamaException("No task found");
                    Task task = tl.getTask(idx);
                    tl.setTaskDone(idx);
                    Response.markedMsg(task);

                } else {

                    Task task;
                    if (input.contains("todo")) {

                        if(input.split(" ").length == 1) throw new KaidamaException("Please enter a description of the todo task");
                        task = new ToDos(input.replace("todo ", ""));

                    } else if (input.contains("deadline")) {
                        input = input.replace("deadline ", "");
                        String[] split = input.split("/by ");
                        if (split[0].isEmpty()) throw new KaidamaException("Please enter a description of the deadline");
                        if (split.length == 1 || split[1].trim().isEmpty()) throw new KaidamaException("Please enter the due date of the task");
                        task = new Deadlines(split[0], split[1]);

                    } else if (input.contains("event")) {

                        String msg = input.replace("event ", "");

                        String[] split = msg.split("/from ");
                        if (split[0].trim().isEmpty()) throw new KaidamaException("Please enter a description of the event");
                        if (split.length == 1) throw new KaidamaException("Please enter the event dates of the task");
                        String[] toSplit = split[1].split("/to ");
                        if(toSplit[0].trim().isEmpty()) throw new KaidamaException("Please enter a start time of the event");
                        if(toSplit.length == 1 || toSplit[1].trim().isEmpty()) throw new KaidamaException("Please enter a end time of the event");
                        task = new Events(split[0], toSplit[0], toSplit[1]);
                    } else {
                        throw new KaidamaException("please enter a valid input (todo, deadline or event only)");
                    }
                    tl.addTask(task);
                    Response.addNewTask(task, tl.getTaskCount());

                }
            } catch (KaidamaException e) {
                Response.errorMsg(String.valueOf(e));
            }
        }


    }
}
