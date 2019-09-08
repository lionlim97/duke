/**
 * Represents a task called todo.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object.
     * @param description Description of a task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo object to a string.
     * @return This returns the string of the Todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
