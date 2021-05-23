import java.util.ArrayList;

/**
 * Interval Class, contains an ArrayList of doubles for the measured
 * temperatures and a start and end interval.
 */
public class Interval {
    int start_timestamp, end_timestamp;
    ArrayList<Double> interval_log = new ArrayList<>();

    /**
     * Constructer used for initializing all the intervals (buckets) in a single sensor
     * @param timestamp Time reference passed to create the bucket
     */
    Interval(int timestamp) {
        this.end_timestamp = timestamp;
        this.start_timestamp = end_timestamp - 3600;
    }
}
