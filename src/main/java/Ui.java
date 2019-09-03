import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected String welcomeMessage = "    Hello! I'm Duke\n"
            + "    What can I do for you?";
    protected String byeMessage = "    Bye. Hope to see you again soon!";
    protected String line = "    ____________________________________________________________";
//    Scanner sc;

    public Ui(){
//        sc = new Scanner(System.in);
    }

/*    public String readCommand(){
        return this.sc.nextLine();
    }*/

    public void showWelcome(){
        System.out.println("Hello from\n");
        System.out.println(logo);
        showLine();
        System.out.println(welcomeMessage);
        showLine();
    }

    public void showBye(){
        System.out.println(byeMessage);
        showLine();
    }

    public void showList(TaskList list){
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= list.taskListSize(); i++) {
            System.out.println("     " + i + "." + list.taskToString(i-1));
        }
        showLine();
    }

    public void showAdd(Task task, int listSize){
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + listSize + (listSize > 1 ? " tasks in the list." : " task in the list."));
        showLine();
    }

    public void showDone(Task task){
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + task.toString());
        showLine();
    }

    public void showDelete(Task task, int listSize){
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + listSize + (listSize > 1 ? " tasks in the list." : " task in the list."));
        showLine();
    }

    public void showFind(TaskList list){
        if(list.taskListSize() == 0) {
            System.out.println("     There are no matching tasks in your list");
        }
        else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 1; i <= list.taskListSize(); i++) {
                System.out.println("     " + i + "." + list.taskToString(i - 1));
            }
        }
        showLine();
    }

    public void showLoadingError(Exception e){
        System.out.println("     File not found" + e.getMessage());
        showLine();
    }

    public void showError(DukeException e){
        System.out.println(e.getMessage());
        showLine();
    }

    public void showLine(){
        System.out.println(line);
    }
}
