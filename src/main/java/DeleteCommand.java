public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if(index >= 0 && index < list.taskListSize()) {
            Task task = list.getTask(index);
            list.removeTask(this.index);
            ui.showDelete(task, list.taskListSize());
        } else throw new DukeException("     \u2639" + " OOPS!!! I'm sorry, but we cannot find the input task number :-(\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
