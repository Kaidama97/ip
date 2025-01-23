import java.util.Objects;
import java.util.Scanner;

public class Kaidama {
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
                Task task = new Task(input);
                tl.addTask(task);
                Response.addNewTask(task);
            }
        }


    }
}
