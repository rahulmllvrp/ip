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
