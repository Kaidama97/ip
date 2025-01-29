package handler;

import exception.KaidamaException;
import task.Task;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private File file;
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> readFile() throws IOException, KaidamaException {
        ArrayList<String> list = new ArrayList<>();
        File file = new File(this.filePath);
        BufferedReader br = null;

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return list;
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

        return list;
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
            for(Task task : taskList) {
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
