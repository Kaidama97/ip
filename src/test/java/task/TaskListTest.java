package task;

import kaidama.task.Events;
import kaidama.task.Task;
import kaidama.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTaskToTaskList_vaildTask_taskAdded() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String fromDateTimeStr = "02/02/2023 1030";
        String toDateTimeStr = "02/02/2023 1030";
        LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeStr, formatter);
        LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeStr, formatter);
        TaskList taskList = new TaskList();
        Task task = new Events(false, "Test Event", fromDateTime, toDateTime);
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
        assertEquals(task, taskList.getTask(1));
    }
}
