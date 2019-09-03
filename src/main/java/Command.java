public abstract class Command {
    public abstract String execute(TaskList list, Ui ui, Storage storage) throws Exception;
    public abstract boolean isExit();
}