import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void addTask(Task task){
        this.list.add(task);
    }

    public void removeTask(int index){
        this.list.remove(index);
    }

    public Task getTask(int index){
        return this.list.get(index);
    }

    public String taskToString(int index){
        return list.get(index).toString();
    }

    public void markAsDone(int index){
        this.list.get(index).setDone(true);
    }

    public int taskListSize(){
        return list.size();
    }

    public TaskList findTask(String key) {
        TaskList temp = new TaskList();
        for (Task task : list) {
            if (task.getDescription().contains(key)) {
                temp.addTask(task);
            }
        }
        return temp;
    }
}
