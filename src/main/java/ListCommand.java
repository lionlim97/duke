public class ListCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws Exception {
        ui.showList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
