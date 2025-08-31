package sage.command;

import sage.storage.Storage;
import sage.task.TaskList;
import sage.ui.Ui;
import sage.exception.SageException;
import java.util.ArrayList;
import sage.task.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }
}
