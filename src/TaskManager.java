public class TaskManager {
    
    private BTree bTree;
    private SegmentTree segmentTree;
    private IntervalTree intervalTree;

    private static final int MAX_ID = 100000;     // adjust if needed

    public TaskManager() {
        bTree = new BTree(3);                  // B-Tree degree
        segmentTree = new SegmentTree(MAX_ID);
        intervalTree = new IntervalTree();
    }

    // Insert
    public void insert(Task task) {

        // 1. check duplicate ID
        if (bTree.search(task.id) != null) {
            System.out.println("Task with this ID already exists");
            return;
        }

        // 2. check time conflict
        if (!intervalTree.insert(task.startTime, task.endTime, task.id)) {
            IntervalNode conflict = intervalTree.conflictSearch(task.startTime, task.endTime);

            if (conflict != null) {
                System.out.println("there is a conflict between " + conflict.taskId + " and " + task.id);
            } else {
                System.out.println("there is a conflict");
            }
            return;
        }

        // 3. insert into B-Tree
        bTree.insert(task);

        // 4. update Segment Tree
        segmentTree.update(task.id, task.value);

        System.out.println("Task inserted");
    }

    // Delete
    public void delete(int id) {

        Task task = bTree.search(id);
        if (task == null) {
            System.out.println("Task not found");
            return;
        }

        // 1. remove from interval tree
        intervalTree.delete(task.startTime, task.endTime, id);

        // 2. remove from B-Tree
        bTree.delete(id);

        // 3. update segment tree
        segmentTree.update(id, 0);

        System.out.println("Task deleted");
    }

    // Update
    public void update(int id, int newStart, int newEnd, int newValue) {

        Task oldTask = bTree.search(id);
        if (oldTask == null) {
            System.out.println("Task not found");
            return;
        }

        // 1. Interval Tree (critical)
        intervalTree.delete(oldTask.startTime, oldTask.endTime, id);

        if (!intervalTree.insert(newStart, newEnd, id)) {
            System.out.println("there is a conflict");

            // rollback
            intervalTree.insert(oldTask.startTime, oldTask.endTime, id);
            return;
        }

        // 2. B-Tree (safe in-place update)
        Task newTask = new Task(id, newStart, newEnd, newValue);
        bTree.update(newTask);

        // 3. Segment Tree (value only)
        segmentTree.update(id, newValue);

        System.out.println("Task updated");
    }
    
    public Task search(int id) {
        // Implement search logic here
        return null;
    }
    public Task search(int start, int end) {
        // Implement search logic here
        return null;
    }
    public void findOverlaps(int start, int end) {
        // Implement overlap finding logic here
    }
    public void printTrees() {
        // Implement tree printing logic here
    }
}
