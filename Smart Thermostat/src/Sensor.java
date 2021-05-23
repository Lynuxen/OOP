import java.util.ArrayList;

/**
 * Sensor Class, has an ID and an ArrayList of intervals.
 */
public class Sensor {
    ArrayList<Interval> log = new ArrayList<>();
    String sensor_id;

    /**
     * Constructor for sensor. Adds all intervals at the start.
     * @param sensor_id The ID of the sensor.
     * @param ref_timestamp The initial reference time, used for calculating
     *                      the intervals.
     */
    Sensor(String sensor_id, int ref_timestamp) {
        this.sensor_id = sensor_id;
        for (int i = 0; i < 24; i++) {
            this.log.add(new Interval(ref_timestamp - 3600 * i));
        }
    }

    /**
     * Used for traversing the buckets.
     * @return The size of the log ArrayList.
     */
    public int sensorSize() {
        return this.log.size();
    }
}