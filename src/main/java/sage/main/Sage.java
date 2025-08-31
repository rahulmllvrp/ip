package sage.main;

import java.time.LocalDateTime;

import sage.command.Command;
import sage.exception.SageException;
import sage.parser.Parser;
import sage.storage.Storage;
import sage.task.TaskList;
import sage.ui.Ui;

/**
 * Represents the main class of the Sage chatbot application.
 * This class initializes the UI, Storage, and TaskList components,
 * and contains the main loop for running the chatbot.
 */
public class Sage {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "./data/sage.txt";

    /**
     * Constructs a new Sage object.
     * Initializes the UI, Storage, and loads tasks from the specified file path.
     * If loading fails, an empty TaskList is created.
     * @param filePath The path to the file where tasks are stored.
     */
    public Sage(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SageException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Sage chatbot application.
     * Displays a welcome message, then enters a loop to read and execute commands
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (SageException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * Main method to start the Sage chatbot application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Sage(FILE_PATH).run();
    }
}