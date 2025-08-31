package sage.command;

import sage.exception.SageException;
import sage.storage.Storage;
import sage.task.Task;
import sage.task.TaskList;
import sage.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        Task removedTask = tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());
        ui.showMessage("Noted. I've removed this task:\n   " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
