import java.util.Scanner;

public class Sage {

    static String name = "sage";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        printLine();
        System.out.println(ANSI_BLUE + " Hello! I'm " + name + ANSI_RESET);
        System.out.println(ANSI_BLUE + " Let's play a game!" + ANSI_RESET);
        System.out.println(ANSI_BLUE + " If you say Sage Says, I will echo you, else, I stay quiet" + ANSI_RESET);
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println( ANSI_BLUE + " Bye. Hope to see you again soon!");
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
            }
            printLine();
            if (!input.toLowerCase().startsWith("sage says")) {
                input = "";
            }
            else {
                input = input.toLowerCase().substring("sage says".length()).trim();
            }
            System.out.println(ANSI_BLUE + input + ANSI_RESET);
            printLine();
        }

        scanner.close();
        exit();
    }
}