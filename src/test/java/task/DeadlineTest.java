package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void creatDeadline_vailddata_created() {
        String expectedOutput = "[D][X] Test Deadline Priority: HIGH (by: Feb 02 2023 1030)";
        String dateTimeStr = "02/02/2023 1030";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        String priority = "HIGH";
        Deadline task = new Deadline(true, "Test Deadline", dateTime, priority);
        assertEquals(expectedOutput, task.toString());
    }

}
