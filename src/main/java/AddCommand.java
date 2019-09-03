public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.task);
        return ui.showAdd(this.task, list.taskListSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
