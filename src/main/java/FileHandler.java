import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
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


}
