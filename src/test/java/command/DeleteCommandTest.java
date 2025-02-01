package command;

import kaidama.command.DeleteCommand;
import kaidama.exception.KaidamaException;
import kaidama.storage.Storage;
import kaidama.task.TaskList;
import kaidama.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class DeleteCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
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
    public void deleteTaskFromTaskList_invalidData_throwsException() {
        storage = new Storage("./data/kaidama.txt");
        taskList = new TaskList();
        ui = new Ui();
        assertThrows(KaidamaException.class,
                () -> new DeleteCommand("delete 123").execute(taskList, ui, storage));
    }
}
