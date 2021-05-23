import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Kirjan Dimovski 322CB
 * @see "readme"
 *
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file_input = new File("therm.in");
        Scanner input = new Scanner(file_input);
        PrintStream output = new PrintStream("therm.out");
        System.setOut(output);

        int number_of_rooms, reference_timestamp;
        double global_temperature;
        ArrayList<Room> house = new ArrayList<>();


        number_of_rooms = input.nextInt();
        global_temperature = input.nextDouble();
        reference_timestamp = input.nextInt();

        // Initialize all the rooms in the house.
        for (int i = 0; i < number_of_rooms; i++) {
            house.add(new Room(input.next(), input.next(), input.nextInt(), reference_timestamp));
        }
        int c = 0;

        // Execute the commands
        while (input.hasNext()) {
            String command = input.next();
            if (command.equals("OBSERVE")) {
                String sensor_id = input.next();
                int observe_timestamp = input.nextInt();
                double observe_temperature = input.nextDouble();

                // Traverse the whole "house", find the sensor and add in the correct bucket.
                // Adding is sorted.
                for (int i = 0; i < number_of_rooms; i++) {
                    if (house.get(i).room_sensor.sensor_id.equals(sensor_id)) {
                        for (int j = house.get(i).room_sensor.log.size() - 1; j >=0 ; j--) {
                            if (house.get(i).room_sensor.log.get(j).start_timestamp <= observe_timestamp &&
                                    house.get(i).room_sensor.log.get(j).end_timestamp >= observe_timestamp &&
                                    !house.get(i).room_sensor.log.get(j).interval_log.contains(observe_temperature)) {
                                house.get(i).room_sensor.log.get(j).interval_log.add(observe_temperature);
                                Collections.sort(house.get(i).room_sensor.log.get(j).interval_log);
                            }
                        }
                    }
                }
            } else if (command.equals("LIST")) {
                String room = input.next();
                int start_interval = input.nextInt();
                int end_interval = input.nextInt();
                for (int i = 0; i < number_of_rooms; i++) {
                    if (room.equals(house.get(i).room_name)) {
                        System.out.print(room);
                        for (int j = 0; j < house.get(i).room_sensor.log.size() ; j++) {
                            if (start_interval <= house.get(i).room_sensor.log.get(j).start_timestamp &&
                                end_interval >= house.get(i).room_sensor.log.get(j).end_timestamp) {
                                for(Double temperature : house.get(i).room_sensor.log.get(j).interval_log) {
                                    System.out.print(" ");
                                    System.out.printf("%.2f",temperature);
                                }
                            }
                        }
                        System.out.println();
                    }
                }
            } else if (command.equals("TEMPERATURE")) {
                global_temperature = input.nextDouble();
            } else if (command.equals("TRIGGER")) {
                String temp = input.next();
                double calculate1 = 0;
                double calculate2 = 0;
                double trigger;
                for (int i = 0; i < number_of_rooms; i++) {
                    calculate1 += house.get(i).surface * house.get(i).minimumFromLastHour(i);
                    calculate2 += house.get(i).surface;
                }
                trigger = calculate1/calculate2;
                if (trigger < global_temperature) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}