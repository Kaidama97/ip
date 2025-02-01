package kaidama.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void setTaskDone(int index) {
        getTask(index).setDone();
    }

    public void setTaskUndone(int index) {
        getTask(index).setUndone();
    }

    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    public Task getTask(int index) {
        return taskList.get(index - 1);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < taskList.size(); i++) {
            str += "    " + (i + 1) + ".";
            str += taskList.get(i).toString() + "\n";
        }
        return str;
    }

}