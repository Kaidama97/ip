public class Response {
    private static String line = ("    ____________________________________________________________");
    public Response() {

    }

    public static void initMsg() {
        System.out.println("    Hello! I'm Kaidama.");
        System.out.println("    What can I do for you?");
    }

    public static void exitMsg() {
        System.out.println(line);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void getList(TaskList list) {
        System.out.println(line);
        System.out.println("    Here are the tasks in your list:");
        System.out.print(list.toString());
        System.out.println(line);
    }

    public static void unMarkedMsg(Task task) {
        System.out.println(line);
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("        " + task.toString());
        System.out.println(line);
    }
    public static void markedMsg(Task task) {
        System.out.println(line);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("        " + task.toString());
        System.out.println(line);
    }

    public static void addNewTask(Task task) {
        System.out.println(line);
        System.out.println("    added: " + task.toString());
        System.out.println(line);
    }


}
