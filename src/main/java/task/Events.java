package task;

public class Events extends Task {

    private String from;
    private String to;
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Events(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        return "E " + super.toStorageString() + " | " + from + "-" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
