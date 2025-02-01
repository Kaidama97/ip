package kaidama.storage;

import kaidama.exception.KaidamaException;
import kaidama.parser.Parser;
import kaidama.task.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
            throw new KaidamaException("error reading file");
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


    public void writeFile(String line) throws KaidamaException, IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            throw new KaidamaException("error writing file");
        } finally {
            if (bw != null) {
                bw.close();
            }
        }

    }


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
            throw new KaidamaException("error writing file");
        } finally {
            if (bw != null) {
                bw.close();
            }
        }

    }


}
