import java.util.ArrayList;
import java.util.Collections;

/**
 * Used for implementing the queue.
 * Contains an array of Passenger
 */
public class Heap {
    ArrayList<Passenger> sorted;
    int flag = 0;
    //int i;

    /**
     * Constructor without arguments
     */
    Heap() {
        this.sorted = new ArrayList<>();
    }

    /**
     * Used for finding the parent of a node
     * @param i index
     * @return
     */
    private int PARENT(int i) {
        return (i - 1) / 2;
    }

    /**
     * Used for finding the left child of a node
     * @param i index
     * @return
     */
    private int LEFT(int i) {
        return (2 * i + 1);
    }

    /**
     * Used for finding the right child of a node
     * @param i index
     * @return
     */
    private int RIGHT(int i) {
        return (2 * i + 2);
    }

    /**
     * Method for finding the size of the heap
     * @return The size
     */
    public int sizeHeap() {
        return this.sorted.size();
    }

    /**
     * Inserting the new passenger sorted using heapify_up
     * @param e "node"
     * @param priority Calculated priority @see Single
     */
    void insert(Passenger e, int priority) {
        sorted.add(e);
        heapify_up(sorted.size() - 1);
    }

    /**
     * Heapifies from the bottom up
     * @param i index
     */
    private void heapify_up(int i) {
        if (sorted.get(PARENT(i)).priority < sorted.get(i).priority) {
            Collections.swap(sorted, i, PARENT(i));
            heapify_up(PARENT(i));
        }
    }

    /**
     * Heapify down. Both Heapify up and down work with Collections.swap
     * @param i index
     */
    private void heapify_down(int i) {
        int left = LEFT(i);
        int right = RIGHT(i);

        int largest = i;

        if (left < sorted.size() && sorted.get(left).priority > sorted.get(i).priority) {
            largest = left;
        }

        if (right < sorted.size() && sorted.get(right).priority > sorted.get(largest).priority) {
            largest = right;
        }

        if (largest != i) {
            Collections.swap(sorted, i, largest);
            heapify_down(largest);
        }
    }

    /**
     * Preorder print of the "heap". The flag is used for printing the first element without a space
     * @param index index
     * @param flag flag
     */
    private void print_preorder(int index, int flag) {
        if (index >= this.sorted.size()) {
            return;
        }
        if (flag == 0) {
            System.out.print(sorted.get(index).id);
            flag++;
            print_preorder(LEFT(index), flag);
            print_preorder(RIGHT(index), flag);
        } else {
            System.out.print(" " + sorted.get(index).id);
            print_preorder(LEFT(index), flag);
            print_preorder(RIGHT(index), flag);
        }

    }

    /**
     * Method required in Main
     */
    void list() {
        print_preorder(0, flag);
    }

    /**
     * Swaps the first with the last element in the heap and removes the last
     * Then it heapifies down from the root
     */
    void embark() {
        Collections.swap(sorted, 0, sorted.size() - 1);
        sorted.remove(sorted.size() - 1);
        heapify_down(0);
    }

    /**
     * Deletes the passenger(s) from the "queue".
     * Sadly, doesn't work for a single passsenger in a family or a group. :(
     * @param e Passenger to be deleted
     */
    void delete(Passenger e) {
        if (e instanceof Family || e instanceof Group) {
            int temp = this.sorted.indexOf(e);
            Collections.swap(sorted, temp, sorted.size() - 1);
            sorted.remove(sorted.size() - 1);
            heapify_down(temp);
        } else {
            for (int i = 0; i < sorted.size(); i++) {
                if(!sorted.get(i).id.contains("s") && e.id.equals(sorted.get(i).id)) {
                    if(e.id.contains("g")) {
                        if(((Group)sorted.get(i)).members.contains(e)) {
                            ((Group)sorted.get(i)).members.remove(e);
                            heapify_down(i);
                        }
                    }
                    else {
                        if(((Family)sorted.get(i)).members.contains(e)) {
                            ((Family)sorted.get(i)).members.remove(e);
                            heapify_down(i);
                        }
                    }
                }
            }
        }
    }
}