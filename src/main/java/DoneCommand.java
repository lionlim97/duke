public class DoneCommand extends Command {

    protected int index;
    protected Task task;

    public DoneCommand(int index){
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if(index >= 0 && index < list.taskListSize()) {
            list.markAsDone(index);
            return ui.showDone(list.getTask(index));
        } else throw new DukeException("     \u2639" + " OOPS!!! I'm sorry, but we cannot find the input task number :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
