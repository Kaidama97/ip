package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.KaidamaException;
import parser.Parser;
import task.Deadline;
import task.Task;

public class StorageTest {
    private static final Path FILE_PATH = Paths.get("./data/kaidama.txt");
    @BeforeEach
    public void setUp() throws IOException {
        Files.deleteIfExists(FILE_PATH); // Clean up before each test
        if (!Files.exists(FILE_PATH.getParent())) {
            Files.createDirectory(FILE_PATH.getParent());
        }
    }

    @AfterEach
    public void clearAll() throws IOException {

        Files.deleteIfExists(FILE_PATH); // Clean up after each test
    }

    @Test
    public void appendNewTasktoFile_validTask_taskAppended() throws IOException, KaidamaException {
        Storage storage = new Storage("./data/kaidama.txt");
        String initialData = "T | 1 | MEDIUM | Sample Todo";
        storage.writeFile(initialData);
        LocalDateTime dateTime = Parser.parseDate("03/02/2019 1800");
        String priority = "HIGH";
        Task deadline = new Deadline(false, "test deadline", dateTime, priority);

        storage.writeFile(deadline.toStorageString());

        List<String> lines = Files.readAllLines(FILE_PATH);
        assertEquals(2, lines.size());
        assertEquals("T | 1 | MEDIUM | Sample Todo", lines.get(0));
        assertEquals("D | 0 | test deadline | HIGH | 03/02/2019 1800", lines.get(1));
    }
}
