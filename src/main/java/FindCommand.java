public class FindCommand extends Command {

    protected String key;

    public FindCommand(String key){
        this.key = key;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws Exception {
        ui.showFind(list.findTask(key));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
