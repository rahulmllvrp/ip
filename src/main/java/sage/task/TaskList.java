package sage.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, mark, unmark, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     * @param tasks The ArrayList of Task objects to initialize the list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     * @param taskIndex The zero-based index of the task to be deleted.
     * @return The Task object that was removed.
     */
    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    /**
     * Retrieves a task from the list at the specified index.
     * @param taskIndex The zero-based index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * Each task is numbered and displayed on a new line.
     * @return A string containing the numbered list of tasks.
     */
    public String getTasksAsString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Marks a task as done at the specified index.
     * @param taskIndex The zero-based index of the task to mark as done.
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Marks a task as not done at the specified index.
     * @param taskIndex The zero-based index of the task to mark as not done.
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).unmarkAsDone();
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
