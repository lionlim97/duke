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

public class Storage {
    protected String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

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

    public void readFile(TaskList list) throws IOException, ParseException {
        ArrayList<String> temp = new ArrayList<>(Files.readAllLines(Paths.get(filePath)));
        for(String string : temp){
            list.addTask(stringToTask(string));
        }
    }

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
            DateFormat format = new SimpleDateFormat("E dd/MM/yyyy hh:mm a");
            Date date = format.parse(string.substring(string.indexOf("by:") + 4, string.indexOf(')')).trim());
            String dateString = format.format(date);
            line = new Deadline(string.substring(7, string.indexOf("by:") - 2), dateString);
        }
        else {
            DateFormat format = new SimpleDateFormat("E dd/MM/yyyy hh:mm a");
            Date date = format.parse(string.substring(string.indexOf("at:") + 4, string.indexOf(')')));
            String dateString = format.format(date);
            line = new Event(string.substring(7, string.indexOf("at:")-2), dateString);
        }
        if(string.contains("\u2713")){
            line.setDone(true);
        }
        return line;
    }
}
