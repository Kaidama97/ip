package task;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
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
