import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        //StringBuilder str = new StringBuilder();
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += "    " + (i + 1) + ". ";
            str += tasks.get(i).toString() + "\n";
        }
        return str;
    }

}