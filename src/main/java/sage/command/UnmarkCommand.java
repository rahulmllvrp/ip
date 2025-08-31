package sage.command;

import sage.exception.SageException;
import sage.storage.Storage;
import sage.task.TaskList;
import sage.ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        tasks.unmarkTask(taskIndex);
        storage.save(tasks.getTasks());
        ui.showMessage("OK, I've marked this task as not done yet:\n   " + tasks.getTask(taskIndex));
    }
}