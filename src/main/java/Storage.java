import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Deals with loading or saving tasks to and from a file.
 */
public class Storage {
    protected String filePath;

    /**
     * Creates Storage object.
     * @param filePath String of the file's filepath
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * This method updates the file in the filepath with the current
     * list of tasks that the user has set.
     * @param list TaskList object with existing tasks to be saved in the file
     * @throws FileNotFoundException On file not found error using filepath
     */
    public void updateFile(TaskList list) throws FileNotFoundException {
        PrintWriter outputStream = new PrintWriter(filePath);
        String textUiFilePath = System.getProperty("user.dir") + "\\text-ui-test\\data\\duke.txt";
        PrintWriter outputStreamTextUi = new PrintWriter(textUiFilePath);
        ArrayList<Task> temp = list.getList();
        for(Task task : temp){
            outputStream.println(task.toString());
            outputStreamTextUi.println(task.toString());
        }
        outputStream.close();
        outputStreamTextUi.close();
    }

    /**
     * This method reads the file and updates the TaskList object with tasks in the file.
     * @param list To update the TaskList object with tasks from the file
     * @throws IOException On input error reading lines in the file
     * @throws ParseException On conversion error from string to Task object
     */
    public void readFile(TaskList list) throws IOException, ParseException {
        ArrayList<String> temp = new ArrayList<>(Files.readAllLines(Paths.get(filePath)));
        for(String string : temp){
            list.addTask(stringToTask(string));
        }
    }

    /**
     * This methods converts the string from the file to Task object.
     * @param string String from the file
     * @return This returns Task object after converting the string from file to Task object
     * @throws ParseException On conversion error from string date to Date object
     */
    private Task stringToTask(String string) throws ParseException {
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
        return line;
    }
}
