import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        String filePath = System.getProperty("user.dir") + "\\data\\duke.txt";
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

    private String run(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        //...
    }

    @Override
    public void start(Stage stage) {
        //...
    }

    public String getResponse(String input) {
        return run(input);
    }
}