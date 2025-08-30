import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws SageException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                tasks.add(parseLineToTask(line));
            }
        } catch (IOException e) {
            throw new SageException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws SageException {
        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new SageException("Error saving tasks to file: " + e.getMessage());
        }
    }

    private Task parseLineToTask(String line) throws SageException {
        String[] parts = line.split(" | ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
            break;
        default:
            throw new SageException("Corrupted data file.");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
