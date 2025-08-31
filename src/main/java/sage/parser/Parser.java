package sage.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sage.command.AddDeadlineCommand;
import sage.command.AddEventCommand;
import sage.command.AddTodoCommand;
import sage.command.Command;
import sage.command.DeleteCommand;
import sage.command.ExitCommand;
import sage.command.FindCommand;
import sage.command.ListCommand;
import sage.command.MarkCommand;
import sage.command.UnmarkCommand;
import sage.exception.SageException;

public class Parser {

    public static Command parse(String fullCommand) throws SageException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMarkCommand(arguments);
        case "unmark":
            return parseUnmarkCommand(arguments);
        case "delete":
            return parseDeleteCommand(arguments);
        case "todo":
            return parseTodoCommand(arguments);
        case "deadline":
            return parseDeadlineCommand(arguments);
        case "event":
            return parseEventCommand(arguments);
        case "find":
            return parseFindCommand(arguments);
        default:
            throw new SageException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseMarkCommand(String arguments) throws SageException {
        if (arguments.isEmpty()) {
            throw new SageException("Please specify the task number to mark.");
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        return new MarkCommand(taskIndex);
    }

    private static Command parseUnmarkCommand(String arguments) throws SageException {
        if (arguments.isEmpty()) {
            throw new SageException("Please specify the task number to unmark.");
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        return new UnmarkCommand(taskIndex);
    }

    private static Command parseDeleteCommand(String arguments) throws SageException {
        if (arguments.isEmpty()) {
            throw new SageException("Please specify the task number to delete.");
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        return new DeleteCommand(taskIndex);
    }

    private static Command parseTodoCommand(String arguments) throws SageException {
        if (arguments.trim().isEmpty()) {
            throw new SageException("The description of a todo cannot be empty.");
        }
        return new AddTodoCommand(arguments);
    }

    private static Command parseDeadlineCommand(String arguments) throws SageException {
        if (arguments.trim().isEmpty()) {
            throw new SageException("The description of a deadline cannot be empty.");
        }
        if (!arguments.contains(" /by ")) {
            throw new SageException("Invalid deadline format. Please use: deadline <description> /by <d/M/yyyy HHmm>");
        }
        String[] deadlineParts = arguments.split(" /by ");
        LocalDateTime by = parseDateTime(deadlineParts[1]);
        return new AddDeadlineCommand(deadlineParts[0], by);
    }

    private static Command parseEventCommand(String arguments) throws SageException {
        if (arguments.trim().isEmpty()) {
            throw new SageException("The description of an event cannot be empty.");
        }
        if (!arguments.contains(" /from ") || !arguments.contains(" /to ")) {
            throw new SageException("Invalid event format. Please use: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
        }
        String[] eventParts = arguments.split(" /from ");
        String[] fromToParts = eventParts[1].split(" /to ");
        LocalDateTime from = parseDateTime(fromToParts[0]);
        LocalDateTime to = parseDateTime(fromToParts[1]);
        return new AddEventCommand(eventParts[0], from, to);
    }

    private static Command parseFindCommand(String arguments) throws SageException {
        if (arguments.trim().isEmpty()) {
            throw new SageException("The keyword for find cannot be empty.");
        }
        return new FindCommand(arguments);
    }

    public static LocalDateTime parseDateTime(String dateTime) throws SageException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new SageException("Invalid date format. Please use: d/M/yyyy HHmm");
        }
    }
}
