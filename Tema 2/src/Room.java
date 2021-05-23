/**
 * Room Class. Has a sensor, name and surface.
 */
public class Room {
    String room_name;
    Sensor room_sensor;
    int surface;

    /**
     * Constructor for a room. Calls the constructor for sensor since every
     * room always has one.
     * @param room_name Room name, denoted by ROOM1, ROOM2 etc.
     * @param sensor_id The sensor ID in the room, used when doing an
     *                  observation.
     * @param surface The surface of the room.
     * @param ref_timestamp The initial reference time, used to create the
     *                      buckets in the sensor.
     */
    Room(String room_name, String sensor_id, int surface, int ref_timestamp) {
        this.room_name = room_name;
        this.room_sensor = new Sensor(sensor_id, ref_timestamp);
        this.surface = surface;
    }

    /**
     * Returns the minimum value from the last hour which has an observation.
     * @param room The room in which to search the value.
     * @return Minimum temperature in the last hour. type Double
     */
    public double minimumFromLastHour(int room) {
        double minimum = 0;

        for (int i = 0; i < this.room_sensor.sensorSize(); i++) {
            if (!this.room_sensor.log.get(i).interval_log.isEmpty()) {
                for (int j = 0; j < this.room_sensor.log.get(i).interval_log.size(); j++) {
                    if (minimum > this.room_sensor.log.get(i).interval_log.get(j)) {
                        minimum = this.room_sensor.log.get(i).interval_log.get(j);
                    } else if (minimum == 0) {
                        minimum = this.room_sensor.log.get(i).interval_log.get(j);
                    }
                }
                break;
            }
        }
        return minimum;
    }
}
