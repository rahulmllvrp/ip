public class Sage {

    static String name = "sage";

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        printLine();
        System.out.println(" Hello! I'm " + name);
        System.out.println(" What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        greet();
        // Add user interaction loop here in the future
        exit();
    }
}