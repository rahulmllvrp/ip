package sage.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getTasksAsString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

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