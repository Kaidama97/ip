package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.KaidamaException;
import parser.Parser;
import task.Task;


/**
 * The Storage class handles reading from and writing to a file located at ./data.
 * Provides methods to load tasks from a file, save tasks to a file, and update tasks in the file.
 */
public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from the file and returns them as a list of Task objects.
     *
     * @return A list of Task objects loaded from the file.
     * @throws KaidamaException If there is an error reading the file.
     * @throws IOException      If an I/O error occurs.
     */
    public ArrayList<Task> readFile() throws KaidamaException, IOException {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(this.filePath);
        BufferedReader br = null;

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        try {
            String readLine;
            br = new BufferedReader(new FileReader(this.filePath));
            readLine = br.readLine();
            while (readLine != null) {
                list.add(readLine);
                readLine = br.readLine();
            }
        } catch (IOException e) {
            throw new KaidamaException("Error reading file: " + e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
        for (String line : list) {
            tasks.add(Parser.inputToTask(line));
        }

        return tasks;
    }

    /**
     * Append a single task to the last line of the file in the predefined format
     *
     * @param line String representation of the task data to append to the file.
     * @throws KaidamaException if there is any error when writing to the file
     * @throws IOException if there is an I/O error
     */
    public void writeFile(String line) throws KaidamaException, IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            throw new KaidamaException("Error writing to file: " + e.getMessage());
        } finally {
            if (bw != null) {
                bw.close();
            }
        }

    }

    /**
     * Updates the entire file with the provided new list of tasks.
     * clear file and rewrite the whole file
     *
     * @param taskList The list of tasks to write to the file.
     * @throws KaidamaException If there is an error writing to the file.
     * @throws IOException      If an I/O error occurs.
     */
    public void updateTaskInFile(ArrayList<Task> taskList) throws KaidamaException, IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write("");
            bw.close();
            bw = new BufferedWriter(new FileWriter(filePath, true));
            for (Task task : taskList) {
                bw.write(task.toStorageString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new KaidamaException("Error updating file: " + e.getMessage());
        } finally {
            if (bw != null) {
                bw.close();
            }
        }

    }
}
