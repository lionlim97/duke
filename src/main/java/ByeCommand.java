public class ByeCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws Exception {
        storage.updateFile(list);
        return ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
