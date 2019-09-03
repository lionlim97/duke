public class ByeCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws Exception {
        storage.updateFile(list);
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
