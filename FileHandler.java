import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FileHandler <T>{
    private final String path;
    private final List<T> contents;
    private final int numberOfLineToRead;

    public FileHandler(String path) {
        this(path, 1);
    }

    public FileHandler(String path, int numberOfLineToRead) {
        this.path = path;
        this.numberOfLineToRead = numberOfLineToRead;
        contents = new ArrayList<>();
        try {
            this.loadData();
        } catch (IOException e) {
            System.out.println("Unable to load data: " + e.getMessage());
        }
    }

    public abstract T decode(String data);

    public abstract String encode(T data);

    public void loadData() throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader(this.path));
        int linesRead = 0;
        StringBuilder batch = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line == null)  break;
            linesRead++;
            batch.append(line).append("\n");
            if (linesRead == this.numberOfLineToRead) {
                contents.add(this.decode(batch.toString()));
                linesRead = 0;
                batch.delete(0, batch.capacity());
            }
        }

    }


    public void appendData(T content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true));
        String data = encode(content);
        writer.write(data);
        writer.newLine();
        writer.flush();

        // also write to in memory messages
        contents.add(content);
    }

    public void printData() {
        for (T item : this.contents) {
            System.out.println(item);
        }
    }
}
