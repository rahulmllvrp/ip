package sage.command;

import sage.storage.Storage;
import sage.task.TaskList;
import sage.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     * Displays all tasks currently in the TaskList to the user via the Ui.
     * @param tasks The TaskList containing the tasks to be listed.
     * @param ui The Ui to display the task list.
     * @param storage The Storage (not used by this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.getTasksAsString());
    }
}