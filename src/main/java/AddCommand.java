public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.task);
        ui.showAdd(this.task, list.taskListSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
