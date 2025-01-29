import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setTaskDone(int index) {
        getTask(index).setDone();
    }

    public void setTaskUndone(int index) {
        getTask(index).setUndone();
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += "    " + (i + 1) + ".";
            str += tasks.get(i).toString() + "\n";
        }
        return str;
    }

}