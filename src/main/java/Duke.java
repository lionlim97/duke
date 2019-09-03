import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            storage.readFile(tasks);
        } catch (IOException | ParseException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                if(sc.hasNextLine()) {
                    String fullCommand = sc.nextLine();
                    ui.showLine();
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                }
                else {
                    ui.showLine();
                    Command bye = new ByeCommand();
                    bye.execute(tasks, ui, storage);
                }
            } catch (DukeException e) {
                ui.showError(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        File file = new File(System.getProperty("user.dir") + "\\data\\duke.txt");
        String filePath = System.getProperty("user.dir") + "\\data\\duke.txt";
        new Duke(filePath).run();
    }
}