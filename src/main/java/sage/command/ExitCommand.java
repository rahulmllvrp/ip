package sage.command;

import sage.storage.Storage;
import sage.task.TaskList;
import sage.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Nothing to execute
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
