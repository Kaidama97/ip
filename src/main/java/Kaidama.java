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
            } else {
                Task task = new Task(input);
                tl.addTask(task);
                Response.addNewTask(task);
            }
        }


    }
}
