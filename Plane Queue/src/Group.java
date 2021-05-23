import java.util.ArrayList;

/**
 * Group class that contains an array of Single
 * @see Single
 */
public class Group extends Passenger {
    ArrayList<Single> members;
    /**
     * Group constructor (no arguments)
     */
    public Group() {
        this.members = new ArrayList<Single>();
    }
}