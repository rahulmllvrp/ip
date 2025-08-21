import java.util.Scanner;

public class Sage {

    private static String[] tasks = new String[100];
    private static int taskCount = 0;

    static String name = "sage";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void printLine() {
        System.out.println(ANSI_YELLOW + "____________________________________________________________" + ANSI_RESET);
    }

    public static void greet() {
        printLine();
        System.out.println(ANSI_GREEN + " Hello! I'm " + name + ANSI_RESET);
        System.out.println(ANSI_GREEN + " I can play 'Sage says' and manage a task list for you." + ANSI_RESET);
        System.out.println(ANSI_GREEN + " What can I do for you?" + ANSI_RESET);
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println(ANSI_BLUE + " Bye. Hope to see you again soon!" + ANSI_RESET);
        printLine();
    }

    public static void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        printLine();
        System.out.println(ANSI_GREEN + " added: " + task + ANSI_RESET);
        printLine();
    }

    public static void listTasks() {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(ANSI_BLUE + (i + 1) + ". " + tasks[i] + ANSI_RESET);
        }
        printLine();
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.toLowerCase().startsWith("sage says")) {
                String output = input.toLowerCase().substring(9).trim();
                printLine();
                System.out.println(ANSI_BLUE + output + ANSI_RESET);
                printLine();
            } else {
                addTask(input);
            }
        }

        scanner.close();
        exit();
    }
}