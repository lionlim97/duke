public class ListCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws Exception {
        return ui.showList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
