package task;

import kaidama.task.Deadline;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void creatDeadline_vailddata_created() {
        String expectedOutput = "[D][X] Test Deadline (by: Feb 02 2023 1030)";
        String dateTimeStr = "02/02/2023 1030";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        Deadline task = new Deadline(true, "Test Deadline", dateTime);
        assertEquals(expectedOutput, task.toString());
    }

}
