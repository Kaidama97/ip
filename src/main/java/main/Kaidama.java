package main;

import command.Command;
import exception.KaidamaException;
import storage.Storage;
import parser.Parser;
import task.TaskList;
import task.Task;
import ui.Ui;

import java.io.IOException;


public class Kaidama {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Task task;


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

    public void run() {
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