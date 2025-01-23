import java.util.Objects;
import java.util.Scanner;

public class Kaidama {
    private String deadlineRegex = "^deadline\\s+(.*?)\\s+/by\\s+(.*)$";
    private String eventRegex = "^event\\s+(.*?)\\s+/from\\s+(.*?)\\s+/to\\s+(.*)$";
    public static void main(String[] args) {
        TaskList tl = new TaskList();
        Scanner sc = new Scanner(System.in);

        Response.initMsg();


        while(true) {
           String input = sc.nextLine();
            if (input.equals("bye")) {
                Response.exitMsg();
                sc.close();
                break;
            } else if (input.equals("list")) {
                Response.getList(tl);
            } else if (input.contains("unmark")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                Task task = tl.getTask(idx);
                tl.setTaskUndone(idx);
                Response.unMarkedMsg(task);
            } else if (input.contains("mark")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                Task task = tl.getTask(idx);
                tl.setTaskDone(idx);
                Response.markedMsg(task);
            } else {
                Task task;
                if(input.contains("todo")) {
                    task = new ToDos(input.replace("todo ", ""));
                } else if (input.contains("deadline")) {
                    input = input.replace("deadline ", "");
                    String[] split = input.split("/by ");

                    task = new Deadlines(split[0], split[1]);

                } else if (input.contains("event")) {
                    input = input.replace("event ", "");
                    String[] split = input.split("/from ");
                    String[] toSplit = split[1].split("/to ");
                    task = new Events(split[0], toSplit[0], toSplit[1]);
                } else {
                    task = new Task(input);
                }
                tl.addTask(task);
                Response.addNewTask(task, tl.getTaskCount());
            }
        }


    }
}
