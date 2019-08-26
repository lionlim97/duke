import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String input, command, line;
    private static int number;
    private static ArrayList<Task> list;

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
            command = sc.next();
            if (command.equals("bye")) {
                System.out.println();
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                break;
            }
            else if(command.equals("list")){
                System.out.println();
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= list.size(); i++){
                    System.out.println(i + "." + list.get(i-1).toString());
                }
                System.out.println();
            }
            else if(command.equals("done")){
                number = sc.nextInt();
                if(number > list.size()){
                    System.out.println("Error, number entered is more than size of list");
                    System.out.println();
                    continue;
                }
                System.out.println("Nice! I've marked this task as done:");
                list.get(number-1).setDone(true);
                System.out.println(list.get(number-1).toString());
                System.out.println();
            }
            else if(command.equals("todo")){
                input = sc.nextLine();
                System.out.println("Got it. I've added this task:");
                list.add(new Todo(input.trim()));
                System.out.println(list.get(list.size()-1).toString());
                System.out.println("Now you have " + list.size() +" tasks in the list.");
                System.out.println();
            }
            else if(command.equals("deadline")){
                input = sc.nextLine();
                String[] arr = input.trim().split(" /by ");
                System.out.println("Got it. I've added this task:");
                list.add(new Deadline(arr[0],arr[1]));
                System.out.println(list.get(list.size()-1).toString());
                System.out.println("Now you have " + list.size() +" tasks in the list.");
                System.out.println();
            }
            else if(command.equals("event")){
                input = sc.nextLine();
                String[] arr = input.trim().split(" /at ");
                System.out.println("Got it. I've added this task:");
                list.add(new Event(arr[0],arr[1]));
                System.out.println(list.get(list.size()-1).toString());
                System.out.println("Now you have " + list.size() +" tasks in the list.");
                System.out.println();
            }
            else{
                input = sc.nextLine();
                if(input.isEmpty()){
                    line = command;
                }
                else{
                    line = command + input;
                }
                Echo();
                list.add(new Task(line));
            }
        }
    }
    private static void Echo(){
        System.out.println();
        System.out.println("added: " + line);
        System.out.println();
    }
}
