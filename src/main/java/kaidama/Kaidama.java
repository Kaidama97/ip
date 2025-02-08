package kaidama;

import java.io.IOException;

import command.Command;
import command.ExitCommand;
import exception.KaidamaException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The Kaidama class represents the main application that manages tasks.
 */
public class Kaidama {
    private static final String DATA_PATH = "./data/kaidama.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructs a Kaidama instance that calls a constructor with the data path as an argument.
     */
    public Kaidama() {
        this(DATA_PATH);
    }
    /**
     * Constructs a Kaidama instance with the specified file path for task storage.
     * @param filePath the file path to store and retrieve tasks.
     * @throws AssertionError If filePath is null.
     */
    private Kaidama(String filePath) {
        assert filePath != null : "filePath cannot be null";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (KaidamaException | IOException e) {
            ui.errorMsg("Error reading file");
            tasks = new TaskList();
        }

    }

    public String getResponse(String userInput) {
        Command c;
        try {
            c = Parser.parseCommand(userInput);
            if (c instanceof ExitCommand) {
                return "bye";
            }
            return c.execute(tasks, ui, storage);
        } catch (KaidamaException | IOException e) {
            return ui.errorMsg(e.getMessage());
        }
    }

    private void run() {
        Ui.initMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KaidamaException | IOException e) {
                ui.errorMsg(e.getMessage());
            }
        }
        ui.exitMsg();
    }
}
