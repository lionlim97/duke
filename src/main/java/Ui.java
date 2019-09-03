public class Ui {
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected String welcomeMessage = "Hello! I'm Duke\n"
            + "What can I do for you?\n";
    protected String byeMessage = "Bye. Hope to see you again soon!\n";
    protected String line = "____________________________________________________________\n";

    public Ui(){
        //...
    }

    public String showWelcome(){
        return "Hello from\n" + logo + welcomeMessage;
    }

    public String showBye(){
        return byeMessage;
    }

    public String showList(TaskList list){
        String listMessage = "Here are the tasks in your list:\n";
        for (int i = 1; i <= list.taskListSize(); i++) {
            listMessage = listMessage + i + "." + list.taskToString(i-1) + "\n";
        }
        return listMessage;
    }

    public String showAdd(Task task, int listSize){
        return "Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + listSize + (listSize > 1 ? " tasks in the list.\n" : " task in the list.\n");
    }

    public String showDone(Task task){
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }

    public String showDelete(Task task, int listSize){
        return "Noted. I've removed this task:\n" + task.toString() + "\n" + "Now you have "
                + listSize + (listSize > 1 ? " tasks in the list.\n" : " task in the list.\n");
    }

    public String showFind(TaskList list){
        if(list.taskListSize() == 0) {
            return "There are no matching tasks in your list\n";
        }
        else {
            String findMessage = "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= list.taskListSize(); i++) {
                findMessage = findMessage + i + "." + list.taskToString(i - 1) + "\n";
            }
            return findMessage;
        }
    }

    public String showLoadingError(Exception e){
        return "File not found" + e.getMessage() + "\n";
    }

    public String showError(DukeException e){
        return e.getMessage() + "\n";
    }

    public void showLine(){
        System.out.println(line);
    }
}
