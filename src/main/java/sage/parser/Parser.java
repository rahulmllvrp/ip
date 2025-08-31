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
import sage.command.ListCommand;
import sage.command.MarkCommand;
import sage.command.UnmarkCommand;
import sage.exception.SageException;

/**
 * Deals with making sense of the user command.
 * Parses user input strings into Command objects and extracts relevant arguments.
 */
public class Parser {

    /**
     * Parses the full command string from the user and returns a corresponding Command object.
     * @param fullCommand The full command string entered by the user.
     * @return A Command object representing the parsed command.
     * @throws SageException If the command is unrecognized or has invalid format/arguments.
     */
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
        default:
            throw new SageException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses arguments for a 'mark' command and returns a MarkCommand object.
     * @param arguments The arguments string for the 'mark' command.
     * @return A MarkCommand object.
     * @throws SageException If the arguments are invalid or missing.
     */
    private static Command parseMarkCommand(String arguments) throws SageException {
        if (arguments.isEmpty()) {
            throw new SageException("Please specify the task number to mark.");
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses arguments for an 'unmark' command and returns an UnmarkCommand object.
     * @param arguments The arguments string for the 'unmark' command.
     * @return An UnmarkCommand object.
     * @throws SageException If the arguments are invalid or missing.
     */
    private static Command parseUnmarkCommand(String arguments) throws SageException {
        if (arguments.isEmpty()) {
            throw new SageException("Please specify the task number to unmark.");
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses arguments for a 'delete' command and returns a DeleteCommand object.
     * @param arguments The arguments string for the 'delete' command.
     * @return A DeleteCommand object.
     * @throws SageException If the arguments are invalid or missing.
     */
    private static Command parseDeleteCommand(String arguments) throws SageException {
        if (arguments.isEmpty()) {
            throw new SageException("Please specify the task number to delete.");
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses arguments for a 'todo' command and returns an AddTodoCommand object.
     * @param arguments The arguments string for the 'todo' command.
     * @return An AddTodoCommand object.
     * @throws SageException If the arguments are invalid or missing.
     */
    private static Command parseTodoCommand(String arguments) throws SageException {
        if (arguments.trim().isEmpty()) {
            throw new SageException("The description of a todo cannot be empty.");
        }
        return new AddTodoCommand(arguments);
    }

    /**
     * Parses arguments for a 'deadline' command and returns an AddDeadlineCommand object.
     * @param arguments The arguments string for the 'deadline' command.
     * @return An AddDeadlineCommand object.
     * @throws SageException If the arguments are invalid or missing, or date format is incorrect.
     */
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

    /**
     * Parses arguments for an 'event' command and returns an AddEventCommand object.
     * @param arguments The arguments string for the 'event' command.
     * @return An AddEventCommand object.
     * @throws SageException If the arguments are invalid or missing, or date format is incorrect.
     */
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

    /**
     * Parses a date and time string into a LocalDateTime object.
     * @param dateTime The date and time string to parse (expected format: d/M/yyyy HHmm).
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws SageException If the date and time string is not in the expected format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws SageException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new SageException("Invalid date format. Please use: d/M/yyyy HHmm");
        }
    }
}