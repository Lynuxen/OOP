/**
 * Super class which contains the priority and the id.
 */
public class Passenger {
    int priority;
    String id;

    /**
     * Constructor without arguments
     */
    public Passenger() {
    }

    /**
     * Sets the priority
     * @param k Priority to be set
     */
    public void setPriority(int k) {
        this.priority = k;
    }
}