package sage.command;

import java.time.LocalDateTime;

import sage.task.Event;

public class AddEventCommand extends AddCommand {
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        super(new Event(description, from, to));
    }
}
