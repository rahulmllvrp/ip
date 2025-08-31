package sage.command;

import java.time.LocalDateTime;

import sage.task.Deadline;

public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(String description, LocalDateTime by) {
        super(new Deadline(description, by));
    }
}