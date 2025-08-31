package sage.command;

import sage.storage.Storage;
import sage.task.TaskList;
import sage.ui.Ui;
import sage.exception.SageException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SageException;

    public boolean isExit() {
        return false;
    }
}
