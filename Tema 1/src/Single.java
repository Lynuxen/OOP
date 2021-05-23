/**
 * Single class used for parsing and creating a single in the heap
 */
public class Single extends Passenger {

    String name;
    int age;
    String ticketType;
    String priorityLeave;
    String specialNeeds;

    /**
     * Used for parsing in the Main method
     * @param id Contains the appropriate tag (f1, g1 etc.)
     * @param name Name of the passenger
     * @param age Age of the passengers
     * @param ticketType The value of the ticket
     * @param priorityLeave Used for urgent cases
     * @param specialNeeds Used to see if the passenger has special needs
     */
    public Single(String id, String name, int age, String ticketType, String priorityLeave, String specialNeeds) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.ticketType = ticketType;
        this.priorityLeave = priorityLeave;
        this.specialNeeds = specialNeeds;
        this.priority = getPriority(id.charAt(0), age, ticketType, priorityLeave, specialNeeds);
        this.id = id;
    }
    /**
     * Constructor without arguments
     */
    public Single() {
    }
    /**
     * Method used for calculating the priority of a single passenger after parsing
     * @param first @see id
     * @param age @see age in constructor
     * @param ticketType @see ticketType in constructor
     * @param priorityLeave @see priorityLeave in constructor
     * @param specialNeeds @see specialNeeds in constructor
     * @return Returns the priority
     */
    public int getPriority(char first, int age, String ticketType, String priorityLeave, String specialNeeds) {
        int priority = 0;
        if(first == 'f') {
            priority += 10;
        }
        else if(first == 'g') {
            priority += 5;
        }
        if(age >= 0 && age < 2) {
            priority += 20;
        }
        else if( age >= 2 && age < 5 ) {
            priority += 10;
        }
        else if( age >= 5 && age < 10 ) {
            priority += 5;
        }
        else if( 60 <= age) {
            priority += 15;
        }
        if(ticketType.equals("b")) {
            priority += 35;
        }
        else if(ticketType.equals("p")) {
            priority += 20;
        }
        if(priorityLeave.equals("true")) {
            priority += 30;
        }
        if(specialNeeds.equals("true")) {
            priority += 100;
        }
        return priority;
    }
}