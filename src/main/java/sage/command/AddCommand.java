package sage.command;

import sage.exception.SageException;
import sage.storage.Storage;
import sage.task.Task;
import sage.task.TaskList;
import sage.ui.Ui;

public abstract class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n   " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
        try {
            storage.save(tasks.getTasks());
        } catch (SageException e) {
            ui.showError(e.getMessage());
        }
    }
}
