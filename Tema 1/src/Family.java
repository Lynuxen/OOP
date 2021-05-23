import java.util.ArrayList;

/**
 * Family class that contains an array of Single
 * @see Single
 */
public class Family extends Passenger {
    ArrayList<Single> members;

    /**
     * Family constructor (no arguments)
     */
    public Family() {
        this.members = new ArrayList<Single>();
    }

}