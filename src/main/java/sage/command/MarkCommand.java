package sage.command;

import sage.exception.SageException;
import sage.storage.Storage;
import sage.task.TaskList;
import sage.ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        tasks.markTask(taskIndex);
        storage.save(tasks.getTasks());
        ui.showMessage("Nice! I've marked this task as done:\n   " + tasks.getTask(taskIndex));
    }
}
