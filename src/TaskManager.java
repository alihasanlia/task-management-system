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

    public void delete(int id) {
        // Implement deletion logic here
    }
    public void update(int id, Task newTask) {
        // Implement update logic here
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
