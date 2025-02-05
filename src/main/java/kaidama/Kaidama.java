package kaidama;

import java.io.IOException;

import command.Command;
import exception.KaidamaException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The Kaidama class represents the main application that manages tasks.
 */
public class Kaidama {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Kaidama instance with the specified file path for task storage.
     * @param filePath the file path to store and retrieve tasks
     */
    public Kaidama(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (KaidamaException | IOException e) {
            ui.errorMsg("Error reading file");
            tasks = new TaskList();
        }

    }

    public static void main(String[] args) {
        new Kaidama("./data/kaidama.txt").run();
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
