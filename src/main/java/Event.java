import java.util.Date;

public class Event extends Task {

    protected Date by;

    public Event(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}