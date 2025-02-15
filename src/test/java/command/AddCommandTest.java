package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.KaidamaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class AddCommandTest {
    private static final Path FILE_PATH = Paths.get("./data/kaidama.txt");
    private TaskList taskList;
    private Ui ui;
    private Storage storage;


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
    public void addEventToTaskList_invalidEndDate_throwsException() throws KaidamaException {
        storage = new Storage("./data/kaidama.txt");
        taskList = new TaskList();
        ui = new Ui();
        String inputString = "event CS2103 Midterms /from 20/02/2025 1800 /to 20/02/2025 1700";
        assertThrows(KaidamaException.class, () ->
                new AddCommand(inputString).execute(taskList, ui, storage));
    }
    @Test
    public void addEventToTaskList_validEventEntry_addTask() throws KaidamaException, IOException {
        storage = new Storage("./data/kaidama.txt");
        taskList = new TaskList();
        ui = new Ui();
        String expectedOutput =
                "    Got it. I've added this task:\n"
                        + "        [E][ ] CS2103 Midterms Priority: "
                        + "MEDIUM (from: Feb 20 2025 1800 to: Feb 20 2025 1900)\n"
                        + "    Now you have 1 task(s) in the list";
        String inputString = "event CS2103 Midterms /from 20/02/2025 1800 /to 20/02/2025 1900";
        String output = new AddCommand(inputString).execute(taskList, ui, storage);
        assertEquals(expectedOutput, output);
    }
}

