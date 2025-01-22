public class Response {
    public Response() {

    }

    public static void initMsg() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Kaidama.");
        System.out.println("    What can I do for you?");
    }

    public static void exitMsg() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void getList(TaskList list) {
        System.out.println("    ____________________________________________________________");
        System.out.print(list.toString());
        System.out.println("    ____________________________________________________________");
    }

    public static void addNewTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + task.toString());
        System.out.println("    ____________________________________________________________");
    }
}
