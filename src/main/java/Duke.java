import java.util.Scanner;

public class Duke {

    private static String input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();

        while (true) {
            input = sc.nextLine();
            if (!input.equals("bye")) {
                Echo();
            } else {
                System.out.println();
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                break;
            }
        }
    }
    private static void Echo(){
        System.out.println();
        System.out.println(input);
        System.out.println();
    }
}