public class Task {
    int id;
    int startTime;
    int endTime;
    int value;

    public Task(int id, int startTime, int endTime, int value) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Task{id=" + id +
               ", start=" + startTime +
               ", end=" + endTime +
               ", value=" + value + "}";
    }
}
