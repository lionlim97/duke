import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {

    private static String input, command;
    private static int number;
    private static ArrayList<Task> list;
    private static File file;

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String workingDir = System.getProperty("user.dir");
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();
        file = new File(workingDir + "\\data\\duke.txt");

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
        readFile();

        while (true) {
            updateFile();
            try {
                command = sc.next();
                if(command.trim().isEmpty()){
                    throw new DukeException("\u2639" + " OOPS!!! Input is empty");
                }
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println();
                    break;
                } else if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + "." + list.get(i - 1).toString());
                    }
                    System.out.println();
                } else if (command.equals("done")) {
                    try {
                        number = sc.nextInt();
                        if (number > list.size()) {
                            System.out.println("Error, number entered is more than size of list");
                            System.out.println();
                            continue;
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        list.get(number - 1).setDone(true);
                        System.out.println(list.get(number - 1).toString());
                        System.out.println();
                    } catch(Exception e){
                        System.out.printf("Please enter the todo as follows:\n" +
                                "done number_on_list\n" +
                                "For example: done 2\n\n");
                    }
                } else if (command.equals("todo")) {
                    try {
                        input = sc.nextLine();
                        if(input.trim().isEmpty()){
                            throw new DukeException("\u2639" + " OOPS!!! The description of a todo cannot be empty.");
                        }
                        System.out.println("Got it. I've added this task:");
                        list.add(new Todo(input.trim()));
                        System.out.println(list.get(list.size() - 1).toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        System.out.println();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.equals("deadline")) {
                    try {
                        input = sc.nextLine();
                        if(input.trim().isEmpty()){
                            throw new DukeException("\u2639" + " OOPS!!! The description of a deadline cannot be empty.");
                        }
                        try{
                            String[] arr = input.trim().split(" /by ");
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                            Date date = formatter.parse(arr[1]);
                            System.out.println("Got it. I've added this task:");
                            list.add(new Deadline(arr[0], date));
                            System.out.println(list.get(list.size() - 1).toString());
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            System.out.println();
                        } catch (Exception e){
                            System.out.printf("Please enter the deadline as follows:\n" +
                                    "deadline name_of_activity /at dd/MM/yyyy HHmm\n" +
                                    "For example: deadline return book /by 2/12/2019 1800\n\n");
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.equals("event")) {
                    try {
                        input = sc.nextLine();
                        if (input.trim().isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        try{
                            String[] arr = input.trim().split(" /at ");
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                            Date date = formatter.parse(arr[1]);
                            System.out.println("Got it. I've added this task:");
                            list.add(new Event(arr[0], date));
                            System.out.println(list.get(list.size() - 1).toString());
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            System.out.println();
                        } catch (Exception e){
                            System.out.printf("Please enter the event time as follows:\n" +
                                    "event name_of_event /at dd/MM/yyyy HHmm\n" +
                                    "For example: event project meeting /at 1/1/2000 0600\n\n");
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if (command.equals("delete")){
                    try {
                        number = sc.nextInt();
                        if (number > list.size()) {
                            System.out.println("Error, number entered is more than size of list");
                            System.out.println();
                            continue;
                        }
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list.get(number - 1).toString());
                        list.remove(number-1);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        System.out.println();
                    } catch(Exception e){
                        System.out.printf("Please enter the delete as follows:\n" +
                                "delete number_on_list\n" +
                                "For example: delete 2\n\n");
                    }
                }
                else {
                    throw new DukeException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void readFile() throws ParseException {
        ArrayList<String> temp;
        temp = new ArrayList<>();
        try {
            temp = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(file))));
        } catch (IOException e){
            e.printStackTrace();
        }
        if(temp.isEmpty()) return;
        for(String string : temp){
            if(string.trim().isEmpty()) {
                System.out.println("Error");
                System.exit(5);
            }
            Task line;
            if(string.contains("[T]")){
                line = new Todo(string.substring(7));
            }
            else if(string.contains("[D]")) {
                DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                Date date = format.parse(string.substring(string.indexOf("by:") + 4, string.indexOf(')')).trim());
                line = new Deadline(string.substring(7, string.indexOf("by:") - 2), date);
            }
            else {
                DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                Date date = format.parse(string.substring(string.indexOf("at:") + 4, string.indexOf(')')));
                line = new Event(string.substring(7, string.indexOf("at:")-2), date);
            }
            if(string.contains("\u2713")){
                line.setDone(true);
            }
            list.add(line);
        }
    }

    private static void updateFile() throws FileNotFoundException{
        PrintWriter outputStream = new PrintWriter(file);
        for(Task task : list){
            outputStream.println(task.toString());
        }
        outputStream.close();
    }
}