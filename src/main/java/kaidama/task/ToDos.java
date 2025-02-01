package kaidama.task;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public ToDos(boolean isDone, String description) {
        super(isDone, description);

    }
    @Override
    public String toStorageString() {
        return "T " + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }
}
