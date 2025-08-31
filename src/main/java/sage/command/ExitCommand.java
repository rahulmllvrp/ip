package sage.command;

import sage.storage.Storage;
import sage.task.TaskList;
import sage.ui.Ui;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     * This command does not perform any operations on the task list or storage.
     * @param tasks The TaskList (not used by this command).
     * @param ui The Ui (not used by this command).
     * @param storage The Storage (not used by this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Nothing to execute
    }

    /**
     * Returns true, indicating that this is an exit command and the chatbot should terminate.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}