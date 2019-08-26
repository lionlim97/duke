import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String input;
    private static ArrayList<String> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();

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
            if (input.equals("bye")) {
                System.out.println();
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                break;
            }
            else if(input.equals("list")){
                System.out.println();
                for(int i = 1; i <= list.size(); i++){
                    System.out.println(i + ". " + list.get(i - 1));
                }
                System.out.println();
            }
            else{
                Echo();
                list.add(input);
            }
        }
    }
    private static void Echo(){
        System.out.println();
        System.out.println("added: " + input);
        System.out.println();
    }
}
